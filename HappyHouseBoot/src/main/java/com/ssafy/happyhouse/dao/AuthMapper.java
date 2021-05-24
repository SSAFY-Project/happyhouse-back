package com.ssafy.happyhouse.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.happyhouse.dto.UserDto;

@Mapper
public interface AuthMapper {
	Optional<UserDto> findByUserId(String userId);
	void registerUser(UserDto userDto);
}
