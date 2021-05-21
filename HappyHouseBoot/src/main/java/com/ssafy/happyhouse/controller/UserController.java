package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.UserService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

//	@GetMapping(value="login")
//	public String login() {
//		return "user/login";
//	}
	
	@PostMapping(value="login")
	private ResponseEntity<UserDto> login(@RequestParam Map<String, String> map, HttpSession session, HttpServletResponse response) throws ServletException, IOException {
		UserDto ud = userService.login(map);

		if(ud != null) {
			session.setAttribute("userinfo", ud);
			
			Cookie cookie = new Cookie("save_id", ud.getUserId());
			cookie.setPath("/");
			if("saveok".equals(map.get("idsave"))) {
				cookie.setMaxAge(60 * 60 * 24 * 365 * 40);//40년간 저장.
			} else {
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			return new ResponseEntity<UserDto>(ud, HttpStatus.OK);
			
		} else {
			return new ResponseEntity<UserDto>(ud,HttpStatus.BAD_REQUEST);
		}
		
	}
	
//	@PostMapping(value="login")
//	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session, HttpServletResponse response) {
//		logger.debug("get in login post");
//		logger.debug(map.get("userId") + " " + map.get("userPwd"));
//		
//		try {
//			UserDto memberDto = userService.login(map);
//			if(memberDto != null) {
//				session.setAttribute("userinfo", memberDto);
//				logger.debug("로그인된 아이디: " + memberDto.getUserId());
//				Cookie cookie = new Cookie("save_id", memberDto.getUserId());
//				cookie.setPath("/");
//				if("saveok".equals(map.get("idsave"))) {
//					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);//40년간 저장.
//				} else {
//					cookie.setMaxAge(0);
//				}
//				response.addCookie(cookie);
//				logger.info("get in login success");
//			} else {
//				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 로그인해 주세요.");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "로그인 중 문제가 발생했습니다.");
//			return "error/error";
//		}
//		return "index";
//	}

	@GetMapping(value="logout")
	private String logout(HttpSession session) throws IOException {
		session.removeAttribute("userinfo");
		return "redirect:/";
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
	
	@GetMapping(value="registerUser")
	public String registerUser() {
		return "user/signUp";
	}
	
	@PostMapping(value="registerUser")
	private ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) throws ServletException, IOException {
		UserDto ud = userService.registerUser(userDto);

		if(userDto != null) {
			return new ResponseEntity<UserDto>(ud, HttpStatus.OK);
			
		} else {
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="getUser")
	private ResponseEntity<UserDto> getUser(@RequestParam("userId") String userId) throws ServletException, IOException {
		UserDto ud = userService.getUser(userId);

		if(ud != null) {
			return new ResponseEntity<UserDto>(ud, HttpStatus.OK);
			
		} else {
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
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
