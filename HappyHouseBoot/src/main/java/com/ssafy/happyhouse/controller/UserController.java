package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.LoginDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.exception.DuplicatedUsernameException;
import com.ssafy.happyhouse.exception.LoginFailedException;
import com.ssafy.happyhouse.service.AuthService;
import com.ssafy.happyhouse.service.UserService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(value="login")
	private ResponseEntity<String> login(@RequestBody LoginDto loginDto) throws ServletException, IOException {
		
		try {String token= authService.login(loginDto);

		 HttpHeaders httpHeaders = new HttpHeaders();
         httpHeaders.add("X-AUTH-TOKEN", token);
         return new ResponseEntity<>(token, httpHeaders, HttpStatus.OK);
		} catch(LoginFailedException e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

	@PutMapping(value="changePwd")
	private ResponseEntity<Void> changePwd(@RequestParam Map<String, String> map) {
		try {
			userService.changePwd(map);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value="register")
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
