package com.ssafy.happyhouse.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.HouseDealDto;

public interface HouseDealMapper {

		// 동별 검색
		public List<HouseDealDto> searchByDong(String dong) throws Exception;
		// 아파트별 검색
		public List<HouseDealDto> searchByAptName(String aptName) throws Exception;
		
		// 모든 거래 정보 출력
		public List<HouseDealDto> listAll() throws Exception;
		
		//코드와 아파트명으로 실시간 거래정보 가져오기
		//Google Map의 특정 마커를 선택하면 그 지역의 매매거래 매물 리스트 가져오는 함수
		public List<HouseDealDto> joinByCode(Map<String, String> map) throws Exception;
}