package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.service.HouseMapService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/map")
public class HouseMapController {
	private static Logger logger = LoggerFactory.getLogger(HouseDealController.class);
	
	@Autowired
	private HouseMapService houseMapService;

	@GetMapping(value = "/sido")
	public List<SidoGugunCodeDto> getSido() throws Exception {
		logger.debug("sido - 호출");
		List<SidoGugunCodeDto> list = houseMapService.getSido();
		return list;
	}
	
	@GetMapping(value = "/gugun")
	public List<SidoGugunCodeDto> getGugun(@RequestParam String sido) throws Exception {
		logger.debug("gugun - 호출");
		List<SidoGugunCodeDto> list = houseMapService.getGugunInSido(sido);	
		return list;
	}
	
	@GetMapping(value = "/dong")
	public List<HouseInfoDto> getDong(@RequestParam String gugun) throws Exception {
		logger.debug("dong - 호출");
		List<HouseInfoDto> list = houseMapService.getDongInGugun(gugun);
		return list;
	}
	
	@GetMapping(value = "/apt")
	public List<HouseInfoDto> getApt(@RequestParam String dong) throws Exception {
		logger.debug("apt - 호출");
		List<HouseInfoDto> list = houseMapService.getAptInDong(dong);
		return list;
	}
}