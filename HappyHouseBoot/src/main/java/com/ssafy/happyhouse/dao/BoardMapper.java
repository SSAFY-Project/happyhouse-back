package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.BoardDto;

@Mapper
public interface BoardMapper {

	public int writeArticle(BoardDto guestBookDto) throws SQLException;
	public List<BoardDto> listArticle(Map<String, Object> map) throws SQLException;
	public int getTotalCount(Map<String, String> map) throws SQLException;
	
	public BoardDto getArticle(int articleno) throws SQLException;
	public int modifyArticle(BoardDto guestBookDto) throws SQLException;
	public void deleteArticle(int articleno) throws SQLException;
	
}
