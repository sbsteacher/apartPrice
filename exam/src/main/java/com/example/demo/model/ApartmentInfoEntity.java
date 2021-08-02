package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApartmentInfoEntity {
	private int iApart;
	@JsonProperty("법정동") private String dong;
	@JsonProperty("지번") private String jibun;
	@JsonProperty("아파트") private String apartmentName;
	@JsonProperty("거래금액")	private String dealAmount;
	@JsonProperty("건축년도") private String buildYear;
	@JsonProperty("년") private String dealYear;
	@JsonProperty("월") private String dealMonth;
	@JsonProperty("일") private String dealDay;
	@JsonProperty("전용면적") private float areaForExclusiveUse;
	@JsonProperty("층") private int flr;	
	private int inLocationCd;
	
	public int getiApart() {
		return iApart;
	}
	public void setiApart(int iApart) {
		this.iApart = iApart;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getDealYear() {
		return dealYear;
	}
	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}
	public String getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}
	public String getDealDay() {
		return dealDay;
	}
	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}
	public float getAreaForExclusiveUse() {
		return areaForExclusiveUse;
	}
	public void setAreaForExclusiveUse(float areaForExclusiveUse) {
		this.areaForExclusiveUse = areaForExclusiveUse;
	}
	public int getFlr() {
		return flr;
	}
	public void setFlr(int flr) {
		this.flr = flr;
	}
	public int getInLocationCd() {
		return inLocationCd;
	}
	public void setInLocationCd(int inLocationCd) {
		this.inLocationCd = inLocationCd;
	}
}
