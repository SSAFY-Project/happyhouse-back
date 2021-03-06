package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="selectUserById")
	private ResponseEntity<UserDto> selectUserById(@RequestParam("userId") String userId) throws ServletException, IOException {
		
		try {
			UserDto ud = userService.getUser(userId);
			return new ResponseEntity<UserDto>(ud, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="selectUsers")
	private ResponseEntity<List<UserDto>> selectUsers() throws ServletException, IOException {
		
		try {
			List<UserDto> list = userService.getUsers();
			return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<UserDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value="deleteUser")
	private ResponseEntity<Void> deleteUser(@RequestParam("userId") String userId) {
		try {
			userService.deleteUser(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
