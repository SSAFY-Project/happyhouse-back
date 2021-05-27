package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.dto.FavoriteDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.BoardService;
import com.ssafy.happyhouse.service.CommentService;

// 게시판 - 잡담, 매물관련
@RestController
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentService commentService;

	// 글 작성 후 작성된 글 정보 반환
	@PostMapping(value = "/write")
	public ResponseEntity<HashMap<Integer, Object>> write(@RequestBody BoardDto boardDto, HttpSession session) {
		HashMap<Integer, Object> map = new HashMap<Integer, Object>();
		
		try {
			int rslt = boardService.writeArticle(boardDto);
			if (rslt == 1) { // 삽입 성공
				map.put(rslt, boardService.getArticle(boardDto.getArticleno()));
			}
			return new ResponseEntity<HashMap<Integer, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HashMap<Integer, Object>>(HttpStatus.BAD_REQUEST); // 400
		}
	}

	// 모든 글 불러오기
	@GetMapping(value = "/list")
	public ResponseEntity<List<BoardDto>> list(@RequestParam Map<String, String> map) {
		String spp = map.get("spp");
		map.put("spp", spp != null ? spp : "10");// sizePerPage

		try {
			List<BoardDto> list = boardService.listArticle(map);
			return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<BoardDto>>(HttpStatus.BAD_REQUEST); // 400
		}
	}

	// 게시글 상세보기 - 댓글 포함
	@GetMapping(value = "/viewDetail")
	public ResponseEntity<HashMap<String, Object>> viewDetail(@RequestParam(value = "articleno") int articleno) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		// 특정 게시글 상세보기
		try {
			BoardDto board = boardService.getArticle(articleno);

			// 해당 게시글 객체
			map.put("BoardDto", board);
			// 해당 게시글 댓글 객체
			List<CommentDto> commentList = commentService.getComments(articleno);
			map.put("CommentList", commentList);

			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HashMap<String, Object>>(HttpStatus.BAD_REQUEST); // 400
		}
	}

	// 수정 처리
	@PutMapping(value = "/modify")
	public ResponseEntity<HashMap<String, Object>> modify(@RequestBody BoardDto boardDto) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		try {
			// 수정 후
			boardService.modifyArticle(boardDto);

			// 수정된 게시글과 댓글 반환
			BoardDto board = boardService.getArticle(boardDto.getArticleno());
			List<CommentDto> commentList = commentService.getComments(boardDto.getArticleno());
			resultMap.put("BoardDto", board);
			resultMap.put("CommentList", commentList);
			return new ResponseEntity<HashMap<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HashMap<String, Object>>(HttpStatus.BAD_REQUEST); // 400
		}
	}

	// 수정 폼 불러올때 기본 틀 채우기 위해 원래 내용 불러오기
	@GetMapping(value = "/modify")
	public ResponseEntity<HashMap<String, Object>> modify(@RequestBody BoardDto boardDto, HttpSession session) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		try {
			BoardDto board = boardService.getArticle(boardDto.getArticleno());
			List<CommentDto> commentList = commentService.getComments(boardDto.getArticleno());
			resultMap.put("BoardDto", board);
			resultMap.put("CommentList", commentList);
			return new ResponseEntity<HashMap<String, Object>>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HashMap<String, Object>>(HttpStatus.BAD_REQUEST); // 400
		}
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Void> delete(@RequestParam("articleno") int articleno, Model model) {
		try {
			boardService.deleteArticle(articleno);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
		}
	}

}
