package com.ssafy.happyhouse.service;

import java.util.List;

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
	public void deleteFav(String userId, int housedealId) throws Exception {
		sqlSession.getMapper(FavoriteMapper.class).deleteFav(userId, housedealId);
	}

	@Override
	public FavoriteDto selectFav(String userId, int housedealId) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).selectFav(userId, housedealId);
	}

	@Override
	public List<FavoriteDto> selectByUserId(String userId) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).selectByUserId(userId);
	}

	@Override
	public List<FavoriteDto> selectByHouseDealId(int housedealId) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).selectByHouseDealId(housedealId);
	}

}
