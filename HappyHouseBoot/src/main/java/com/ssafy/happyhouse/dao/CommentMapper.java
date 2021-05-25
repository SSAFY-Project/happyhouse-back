package com.ssafy.happyhouse.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.CommentDto;

@Mapper
public interface CommentMapper {
	public int inputComment(HashMap<String, Object> map);
	public int updateComment(HashMap<String, Object> map);
	public int deleteComment(int commentId);
	public List<CommentDto> getComments(int boardId);
	public HashMap<String, Object> getComment(int boardId);
}
