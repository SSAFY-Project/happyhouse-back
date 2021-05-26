package com.ssafy.happyhouse.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.dto.FavoriteDto;

@Mapper
public interface FavoriteMapper {
	public int insertFav(FavoriteDto favVO) throws Exception;
	public int deleteFav(@Param("userId") String userId, @Param("housedealId") int housedealId) throws Exception;
	public FavoriteDto selectFav(@Param("userId") String userId, @Param("housedealId") int housedealId) throws Exception;
	public List<FavoriteDto> selectByUserId(String userId) throws Exception;
	public List<FavoriteDto> selectByHouseDealId(int housedealId) throws Exception;
}
