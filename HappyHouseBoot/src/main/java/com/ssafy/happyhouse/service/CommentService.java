package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.CommentDto;

public interface CommentService {
	public int inputComment(HashMap<String, Object> map);
	public int updateComment(HashMap<String, Object> map);
	public int deleteComment(int commentId);
	public List<CommentDto> getComments(int articleno);
	public HashMap<String, Object> getComment(int articleno);
}