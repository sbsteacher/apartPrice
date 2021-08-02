package com.example.demo;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.ApartmentInfoEntity;
import com.example.demo.model.LocationCodeEntity;
import com.example.demo.model.SearchDTO;

@Mapper
public interface HomeMapper {
	int insApartmentInfo(ApartmentInfoEntity param);
	List<LocationCodeEntity> selLocationCodeList();
	LocationCodeEntity selLocationCode(SearchDTO param);
	List<ApartmentInfoEntity> selApartmentInfoList(SearchDTO param);
}
