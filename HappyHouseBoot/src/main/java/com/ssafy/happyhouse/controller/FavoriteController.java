package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.FavoriteDto;
import com.ssafy.happyhouse.service.FavoriteService;

// 관심매물 구현
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

	private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);
	
	@Autowired
	private FavoriteService favoriteService;

	
	// 관심매물 insert
	@PostMapping(value="/insertFav")
	private ResponseEntity<Integer> insertFav(@RequestBody FavoriteDto favoriteDto) throws Exception {
		logger.debug("insertFav - 호출");
		try {
			int rslt = favoriteService.insertFav(favoriteDto);
			return new ResponseEntity<Integer>(rslt, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 관심매물 정보 delete
	@DeleteMapping(value="deleteFav")
	private ResponseEntity<Integer> deleteFav(@RequestParam String userId, @RequestParam int housedealId) throws Exception {
		logger.debug("deleteFav - 호출");
		
		try {
			int rslt = favoriteService.deleteFav(userId, housedealId);
			return new ResponseEntity<Integer>(rslt, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// 관심매물 정보 1개 가져오기
	@GetMapping(value="getFavInfo")
	private ResponseEntity<FavoriteDto> selectFav(@RequestParam String userId, @RequestParam int housedealId) throws Exception {
		logger.debug("selectFav - 호출");
		
		FavoriteDto fd = favoriteService.selectFav(userId, housedealId);
		if (fd != null) {
			return new ResponseEntity<FavoriteDto>(fd, HttpStatus.OK);
		} else {
			return new ResponseEntity<FavoriteDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 해당 유저에 대한 관심매물 정보 모두 가져오기
	@GetMapping(value="getFavListByUserId")
	private ResponseEntity<List<FavoriteDto>> selectByUserId(@RequestParam String userId) throws Exception {
		logger.debug("selectByUserId - 호출");
		
		List<FavoriteDto> list = favoriteService.selectByUserId(userId);	
		if (list != null) {
			return new ResponseEntity<List<FavoriteDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<FavoriteDto>>(HttpStatus.BAD_REQUEST);
		}
	}
		
	// 해당 지역에 대한 관심매물 정보 모두 가져오기
	@GetMapping(value="getFavListByHouseDealId")
	private ResponseEntity<List<FavoriteDto>> selectByHouseDealId(@RequestParam int housedealId) throws Exception {
		List<FavoriteDto> list = favoriteService.selectByHouseDealId(housedealId);
		if (list != null) {
			return new ResponseEntity<List<FavoriteDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<FavoriteDto>>(HttpStatus.BAD_REQUEST);
		}
	}
}
