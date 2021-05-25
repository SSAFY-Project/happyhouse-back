package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.FavoriteDto;

public interface FavoriteService {
	public int insertFav(FavoriteDto favVO) throws Exception;
	public int deleteFav(String userId, int housedealId) throws Exception;
	public FavoriteDto selectFav(String userId, int housedealId) throws Exception;
	public List<FavoriteDto> selectByUserId(String userId) throws Exception;
	public List<FavoriteDto> selectByHouseDealId(int housedealId) throws Exception;
}
