package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.BoardDto;

public interface BoardService {

	public int writeArticle(BoardDto boardDto) throws Exception;
	public List<BoardDto> listArticle(Map<String, String> map) throws Exception;
	
	public BoardDto getArticle(int articleno) throws Exception;
	public int modifyArticle(BoardDto boardDto) throws Exception;
	public void deleteArticle(int articleno) throws Exception;
	
}
