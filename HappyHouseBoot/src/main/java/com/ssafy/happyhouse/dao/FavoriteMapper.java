package com.ssafy.happyhouse.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.dto.FavoriteDto;

public interface FavoriteMapper {
	public FavoriteDto insertFav(FavoriteDto favVO) throws Exception;
	public FavoriteDto updateFav(FavoriteDto favVO) throws Exception;
	public void deleteFav(@Param("userId") String userId, @Param("baseCode") long baseCode) throws Exception;
	public FavoriteDto selectFav(@Param("userId") String userId, @Param("baseCode") long baseCode) throws Exception;
	public List<FavoriteDto> selectByUserId(String userId) throws Exception;
	public List<FavoriteDto> selectByBaseCode(long baseCode) throws Exception;
}
