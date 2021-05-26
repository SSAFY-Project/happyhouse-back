package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.HouseDealMapper;
import com.ssafy.happyhouse.dto.HouseDealDto;

@Service
public class HouseDealServiceImpl implements HouseDealService {

	@Autowired
	private SqlSession sqlSession;
//	@Autowired
//	private HouseDealMapper houseDealDao;
	
	@Override
	public List<HouseDealDto> searchByDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).searchByDong(dong);
//		return houseDealDao.searchByDong(dong);
	}

	@Override
	public List<HouseDealDto> searchByAptName(String aptName) throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).searchByAptName(aptName);
//		return houseDealDao.searchByAptName(AptName);
	}

	@Override
	public List<HouseDealDto> listAll() throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).listAll();
//		return houseDealDao.listAll();
	}

	@Override
	public List<HouseDealDto> joinByCode(String dong, String aptName) throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).joinByCode(dong, aptName);
//		return houseDealDao.joinByCode(dong, AptName);
	}
}
