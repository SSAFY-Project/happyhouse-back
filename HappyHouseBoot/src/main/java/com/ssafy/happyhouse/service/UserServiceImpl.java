package com.ssafy.happyhouse.service;

import java.util.List;
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
	public void changePwd(Map<String, String> map) {
		sqlSession.getMapper(UserMapper.class).changePwd(map);
	}
	
	@Override
	public UserDto getUser(String userId) {
		logger.info("get in getUser");
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

	@Override
	public List<UserDto> getUsers() {
		return sqlSession.getMapper(UserMapper.class).getUsers();
	}

}
