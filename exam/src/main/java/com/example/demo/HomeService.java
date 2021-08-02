package com.example.demo;


import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.ApartmentInfoEntity;
import com.example.demo.model.LocationCodeEntity;
import com.example.demo.model.SearchDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HomeService {
	@Autowired private HomeMapper mapper;
	
	public List<LocationCodeEntity> selLocationCodeList() {
		return mapper.selLocationCodeList();
	}
	
	public List<ApartmentInfoEntity> selResultList(SearchDTO param) {
		List<ApartmentInfoEntity> dbList = mapper.selApartmentInfoList(param);

        if(dbList.size() > 0) { return dbList; }

        final String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
        final String SERVICE_KEY = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX+NgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA==";
                
        
        String dealYM = String.format("%s%02d", param.getDealYear(), Integer.parseInt(param.getDealMonth()));
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("LAWD_CD", param.getExLocationCd())
                .queryParam("DEAL_YMD", dealYM)
                .queryParam("serviceKey", SERVICE_KEY)
                .queryParam("numOfRows", "5000")
                .build(false);
       
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        ResponseEntity<String> respEntity = rest.exchange(builder.toUriString(), HttpMethod.GET, null, String.class);
        String result = respEntity.getBody();
        
        ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = null;
        ApartmentInfoEntity[] list = null;
        
        try {
            jsonNode = om.readTree(result);
            
            String resultCode = om.treeToValue(jsonNode.path("response").path("header").path("resultCode"), String.class);
            
            if(resultCode.equals("99")) {
            	System.out.println("ERROR!!!!");
            	return null;
            }
            
            list = om.treeToValue(jsonNode.path("response").path("body").path("items").path("item")
                    , ApartmentInfoEntity[].class);
            System.out.println("list.length : " + list.length);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        LocationCodeEntity code = mapper.selLocationCode(param);
        
        if(list != null && list.length > 0) {
        	for(ApartmentInfoEntity item : list) {
        		item.setInLocationCd(code.getInLocationCd());        		
        		mapper.insApartmentInfo(item);
            }        	
        	return mapper.selApartmentInfoList(param);
        }
		return null;
	}
}
