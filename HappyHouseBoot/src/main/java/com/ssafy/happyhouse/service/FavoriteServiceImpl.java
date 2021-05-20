package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.FavoriteMapper;
import com.ssafy.happyhouse.dto.FavoriteDto;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	private static final Logger logger = LoggerFactory.getLogger(FavoriteServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public FavoriteDto insertFav(FavoriteDto favVO) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).insertFav(favVO);
	}

	@Override
	public FavoriteDto updateFav(FavoriteDto favVO) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).updateFav(favVO);
		
	}

	@Override
	public FavoriteDto deleteFav(String userId, long baseCode) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).deleteFav(userId, baseCode);
	}

	@Override
	public FavoriteDto selectFav(String userId, long baseCode) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).selectFav(userId, baseCode);
	}

	@Override
	public List<FavoriteDto> selectByUserId(String userId) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).selectByUserId(userId);
	}

	@Override
	public List<FavoriteDto> selectByBaseCode(long baseCode) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).selectByBaseCode(baseCode);
	}

}
