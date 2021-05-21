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
public class SidoGugunCodeDto {

	private String sidoCode;
	private String sidoName;
	private String gugunCode;
	private String gugunName;

}
