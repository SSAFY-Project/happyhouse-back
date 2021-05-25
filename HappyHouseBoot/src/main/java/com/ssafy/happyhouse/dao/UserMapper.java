package com.ssafy.happyhouse.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.happyhouse.dto.UserDto;

@Mapper
public interface UserMapper {

	// 비밀번호 변경
	void changePwd(Map<String, String> map);

	// 회원정보 수정 - 이메일, 주소, 전화번호  수정 가능
	UserDto modifyUser(String userId);

	// 회원정보 조회
	UserDto getUser(String userId);
	
	// 전체 회원 조회
	List<UserDto> getUsers();

	// 회원탈퇴
	void deleteUser(String userId);
	
}
