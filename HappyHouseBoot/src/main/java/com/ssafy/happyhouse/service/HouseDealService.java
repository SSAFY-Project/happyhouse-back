package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.HouseDealDto;


public interface HouseDealService {

	// 동별 검색
	public List<HouseDealDto> searchByDong(String dong) throws Exception;
	// 아파트별 검색
	public List<HouseDealDto> searchByAptName(String AptName) throws Exception;
	// 모든 거래 정보 출력
	public List<HouseDealDto> listAll() throws Exception;
	
	public List<HouseDealDto> joinByCode(Map<String, String> map) throws Exception;
}