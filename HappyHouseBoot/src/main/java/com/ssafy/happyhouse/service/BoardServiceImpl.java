package com.ssafy.happyhouse.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.BoardMapper;
import com.ssafy.happyhouse.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int writeArticle(BoardDto guestBookDto) throws Exception {
		if(guestBookDto.getSubject() == null || guestBookDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeArticle(guestBookDto);
	}

	@Override
	public List<BoardDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(BoardMapper.class).listArticle(param);
	}

	@Override
	public BoardDto getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).getArticle(articleno);
	}

	@Override
	public int modifyArticle(BoardDto guestBookDto) throws Exception {
		return sqlSession.getMapper(BoardMapper.class).modifyArticle(guestBookDto);
	}

	@Override
	public void deleteArticle(int articleno) throws Exception {
		sqlSession.getMapper(BoardMapper.class).deleteArticle(articleno);
	}

}
