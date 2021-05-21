package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dao.CommentMapper;
import com.ssafy.happyhouse.dto.CommentDto;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{
	
	private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int inputComment(HashMap<String, Object> map) {
		return sqlSession.getMapper(CommentMapper.class).inputComment(map);
	}

	@Override
	public int updateComment(HashMap<String, Object> map) {
		return sqlSession.getMapper(CommentMapper.class).updateComment(map);
	}

	@Override
	public int deleteComment(int commentId) {
		return sqlSession.getMapper(CommentMapper.class).deleteComment(commentId);
	}

	@Override
	public List<CommentDto> getComments(int articleno) {
		return sqlSession.getMapper(CommentMapper.class).getComments(articleno);
	}

	@Override
	public HashMap<String, Object> getComment(int articleno) {
		return sqlSession.getMapper(CommentMapper.class).getComment(articleno);
	}
} 