package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.service.HouseDealService;

// 동별, 아파트별 검색 기능 구현
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/search")
public class HouseDealController {
	private static Logger logger = LoggerFactory.getLogger(HouseDealController.class);

	@Autowired
	private HouseDealService houseDealService;

	@GetMapping(value = "/dong")
	public ResponseEntity<Map<String, List<HouseDealDto>>> searchByDong(@RequestParam("searchString") String dong) {
		logger.debug("searchByDong - 호출");
		try {
			Map<String, List<HouseDealDto>> resultMap = new HashMap<String, List<HouseDealDto>>();
			
			List<HouseDealDto> list = houseDealService.searchByDong(dong);
			resultMap.put("dongList", list);
			return new ResponseEntity<Map<String, List<HouseDealDto>>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, List<HouseDealDto>>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/aptname")
	public ResponseEntity<Map<String, List<HouseDealDto>>> searchByAptName(@RequestParam("searchString") String aptName)  {
		logger.debug("searchByAptName - 호출");
		try {
			Map<String, List<HouseDealDto>> resultMap = new HashMap<String, List<HouseDealDto>>();
			
			List<HouseDealDto> list = houseDealService.searchByAptName(aptName);
			resultMap.put("aptList", list);
			return new ResponseEntity<Map<String, List<HouseDealDto>>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, List<HouseDealDto>>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/all")
	public ResponseEntity<Map<String, List<HouseDealDto>>> searchAll() throws Exception {
		logger.debug("searchAll - 호출");
		try {
			Map<String, List<HouseDealDto>> resultMap = new HashMap<String, List<HouseDealDto>>();
			
			List<HouseDealDto> list = houseDealService.listAll();
			resultMap.put("totalList", list);
			return new ResponseEntity<Map<String, List<HouseDealDto>>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, List<HouseDealDto>>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/markerlist")
	public ResponseEntity<Map<String, List<HouseDealDto>>> searchMarkerList(@RequestBody Map<String, String> map) throws Exception {
		logger.debug("searchMarkerList - 호출");
		try {
			Map<String, List<HouseDealDto>> resultMap = new HashMap<String, List<HouseDealDto>>();
			
			List<HouseDealDto> list = houseDealService.joinByCode(map);
			resultMap.put("markerList", list);
			return new ResponseEntity<Map<String, List<HouseDealDto>>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, List<HouseDealDto>>>(HttpStatus.BAD_REQUEST);
		}
	}
}
