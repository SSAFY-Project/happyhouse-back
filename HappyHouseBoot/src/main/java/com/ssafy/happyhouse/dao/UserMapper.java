package com.ssafy.happyhouse.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.happyhouse.dto.UserDto;

public interface UserMapper {
	// 로그인
	// UserDto login(Map<String, String> map);

	// 비밀번호 변경
	void changePwd(Map<String, String> map);

	// 회원정보 수정 - 이메일, 주소, 전화번호  수정 가능
	UserDto modifyUser(String userId);

	// 회원정보 조회
	UserDto getUser(String userId);

	// 회원가입
	// UserDto registerUser(UserDto user);

	// 회원탈퇴
	void deleteUser(String userId);
	
}
