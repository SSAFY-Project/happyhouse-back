package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.happyhouse.dto.LoginDto;
import com.ssafy.happyhouse.dto.UserDto;

public interface UserService {

	// 비밀번호 변경
	void changePwd(Map<String, String> map);

	// 회원 정보 조회
	UserDto getUser(String userId);

	// 회원 정보 수정
	UserDto modifyUser(String userId);
		
	// 회원탈퇴
	void deleteUser(String userId);

}
