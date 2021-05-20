package com.ssafy.happyhouse.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.UserMapper;
import com.ssafy.happyhouse.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserDto login(Map<String, String> map) {
		if(map.get("userId") == null || map.get("userPwd")== null)
			return null;
		return sqlSession.getMapper(UserMapper.class).login(map);
	}

	@Override
	public void changePwd(Map<String, String> map) {
		sqlSession.getMapper(UserMapper.class).changePwd(map);
	}

	@Override
	public UserDto registerUser(UserDto user) {
		return sqlSession.getMapper(UserMapper.class).registerUser(user);
	}
	
	@Override
	public UserDto getUser(String userId) {
		return sqlSession.getMapper(UserMapper.class).getUser(userId);
	}
	
	@Override
	public UserDto modifyUser(String userId) {
		return sqlSession.getMapper(UserMapper.class).modifyUser(userId);
	}

	@Override
	public void deleteUser(String userId) {
		sqlSession.getMapper(UserMapper.class).deleteUser(userId);
	}

}
