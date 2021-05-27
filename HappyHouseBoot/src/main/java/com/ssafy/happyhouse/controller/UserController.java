package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dao.AuthMapper;
import com.ssafy.happyhouse.dto.FavoriteDto;
import com.ssafy.happyhouse.dto.LoginDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.exception.DuplicatedUsernameException;
import com.ssafy.happyhouse.exception.LoginFailedException;
import com.ssafy.happyhouse.jwt.JwtTokenProvider;
import com.ssafy.happyhouse.service.AuthService;
import com.ssafy.happyhouse.service.FavoriteService;
import com.ssafy.happyhouse.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;
	
	@Autowired
	private FavoriteService FavoriteService;
	
	private final PasswordEncoder passwordEncoder;

	@PostMapping(value = "login")
	private ResponseEntity<HashMap<String, Object>> login(@RequestBody LoginDto loginDto)
			throws Exception {

		HashMap<String, Object> resultMap=new HashMap<String, Object>();
		
		try {
			String token = authService.login(loginDto);
			UserDto user=userService.getUser(loginDto.getUsername());
			List<FavoriteDto> favorite = FavoriteService.selectByUserId(loginDto.getUsername());
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("X-AUTH-TOKEN", token);

			// 결과에 token이라는 이름으로 생성된 토큰값 넘기기
			resultMap.put("token", token);
			resultMap.put("user", user);
			resultMap.put("favorite", favorite);
			return new ResponseEntity<HashMap<String, Object>>(resultMap, httpHeaders, HttpStatus.OK);
		} catch (LoginFailedException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "changePwd")
	private ResponseEntity<Integer> changePwd(@RequestBody Map<String, String> map) {
		try {
			// 비밀번호 암호화 시켜 보내기
			String encoded_pw=passwordEncoder.encode(map.get("userPwd"));
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("userId", map.get("userId"));
			resultMap.put("userPwd", encoded_pw);
			
			logger.debug("encoded password : " + encoded_pw);
			int rslt = userService.changePwd(resultMap);
			return new ResponseEntity<Integer>(rslt, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "modify")
	private ResponseEntity<Map<Integer, UserDto>> modifyUser(@RequestBody Map<String, String> map) {
		try {
			// 아이디를 받아 해당 유저 정보 바꾸기
			int rslt = userService.modifyUser(map);
			// 바뀐 정보 다시 return
			HashMap<Integer, UserDto> resultMap = new HashMap<Integer, UserDto>();
			UserDto userDto = userService.getUser(map.get("userId"));
			resultMap.put(rslt, userDto);
			return new ResponseEntity<Map<Integer, UserDto>>(resultMap, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<Integer, UserDto>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "register")
	private ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {

		try {
			UserDto ud = authService.register(userDto);

			return new ResponseEntity<UserDto>(ud, HttpStatus.CREATED); // 201
		} catch (DuplicatedUsernameException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}
	}

}
