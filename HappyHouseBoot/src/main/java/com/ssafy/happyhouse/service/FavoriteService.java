package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.happyhouse.dto.FavoriteDto;

public interface FavoriteService {
	public FavoriteDto insertFav(FavoriteDto favVO) throws Exception;
	public FavoriteDto updateFav(FavoriteDto favVO) throws Exception;
	public void deleteFav(String userId, long baseCode) throws Exception;
	public FavoriteDto selectFav(String userId, long baseCode) throws Exception;
	public List<FavoriteDto> selectByUserId(String userId) throws Exception;
	public List<FavoriteDto> selectByBaseCode(long areaCode) throws Exception;
}
