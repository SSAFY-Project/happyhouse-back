package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.AuthMapper;
import com.ssafy.happyhouse.dao.UserMapper;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private SqlSession sqlSession;

	@Transactional
	@Override
	public int changePwd(Map<String, String> map) {
		
		return sqlSession.getMapper(UserMapper.class).changePwd(map);
	}
	
	@Override
	public UserDto getUser(String userId) {
		return sqlSession.getMapper(UserMapper.class).getUser(userId);
	}
	
	@Transactional
	@Override
	public int modifyUser(Map<String, String> map) {
		return sqlSession.getMapper(UserMapper.class).modifyUser(map);
	}

	@Override
	public void deleteUser(String userId) {
		sqlSession.getMapper(UserMapper.class).deleteUser(userId);
	}

	@Override
	public List<UserDto> getUsers() {
		return sqlSession.getMapper(UserMapper.class).getUsers();
	}

}
