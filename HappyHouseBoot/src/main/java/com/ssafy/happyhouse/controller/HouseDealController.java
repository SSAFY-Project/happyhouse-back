package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/dong", method = RequestMethod.GET)
	public String searchByDong(Model model, @RequestParam("searchString") String dong) throws Exception {
		logger.debug("searchByDong - 호출");
		List<HouseDealDto> list = houseDealService.searchByDong(dong);
		model.addAttribute("list", list);
		return "searchFilter";
	}

	@RequestMapping(value = "/aptname", method = RequestMethod.GET)
	public String searchByAptName(Model model, @RequestParam("searchString") String aptName) throws Exception {
		logger.debug("searchByAptName - 호출");
		List<HouseDealDto> list = houseDealService.searchByAptName(aptName);
		model.addAttribute("list", list);
		return "searchFilter";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String searchAll(Model model) throws Exception {
		logger.debug("searchAll - 호출");
		List<HouseDealDto> list = houseDealService.listAll();
		model.addAttribute("list", list);
		return "searchFilter";
	}

	@RequestMapping(value = "/markerlist", method = RequestMethod.GET)
	public List<HouseDealDto> searchMarkerList(@RequestParam Map<String, String> map) throws Exception {
		logger.debug("searchMarkerList - 호출");
		List<HouseDealDto> list = houseDealService.joinByCode(map);
		return list;
	}
}
