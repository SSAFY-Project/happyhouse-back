package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.BoardDto;

public interface BoardMapper {

	public BoardDto writeArticle(BoardDto guestBookDto) throws SQLException;
	public List<BoardDto> listArticle(Map<String, Object> map) throws SQLException;
	public int getTotalCount(Map<String, String> map) throws SQLException;
	
	public BoardDto getArticle(int articleno) throws SQLException;
	public BoardDto modifyArticle(BoardDto guestBookDto) throws SQLException;
	public void deleteArticle(int articleno) throws SQLException;
	
}
