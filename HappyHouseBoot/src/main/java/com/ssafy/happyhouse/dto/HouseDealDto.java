package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// 아파트, 주택 실거래 정보
public class HouseDealDto {
	int no;
	String dong;
	String aptName;
	String code;
	String dealAmount;
	String buildYear;
	String dealYear;
	String dealMonth;
	String dealDay;
	String area;
	String floor;
	String jibun;
	String type;
	String rentMoney;
	
}
