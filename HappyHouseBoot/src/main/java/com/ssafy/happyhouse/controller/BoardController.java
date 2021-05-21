package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentService commentService;

	// 글 작성
	@PostMapping(value = "/write")
	public ResponseEntity<BoardDto> write(@RequestBody BoardDto boardDto, HttpSession session) {
		UserDto UserDto = (UserDto) session.getAttribute("userinfo");
		if (UserDto != null) {
			boardDto.setUserid(UserDto.getUserId());
			try {
				BoardDto board = boardService.writeArticle(boardDto);
				return new ResponseEntity<BoardDto>(board, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<BoardDto>(HttpStatus.BAD_REQUEST); // 400
			}
		} else {
			return new ResponseEntity<BoardDto>(HttpStatus.UNAUTHORIZED); // 401
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

		// 특정 게시글 상세보기
		try {
			BoardDto board = boardService.getArticle(articleno);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			// 해당 게시글 객체
			map.put("BoardDto", board);
			//  해당 게시글 댓글 객체
			List<CommentDto> commentList = commentService.getComments(articleno);
			map.put("CommentList", commentList);
			
			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HashMap<String, Object>>(HttpStatus.BAD_REQUEST); // 400
		}
	}

	// 수정 폼 불러올때 원래 내용 가져오기
	@GetMapping(value = "/modify")
	public ResponseEntity<BoardDto> modify(@RequestParam("articleno") int articleno) {
		try {
			BoardDto boardDto = boardService.getArticle(articleno);
			return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<BoardDto>(HttpStatus.BAD_REQUEST); // 400
		}
	}

	// 수정 처리
	@PutMapping(value = "/modify")
	public ResponseEntity<BoardDto> modify(@RequestBody BoardDto boardDto, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userinfo");
		if (userDto != null) {
			userDto.setUserId(userDto.getUserId());
			try {
				boardService.modifyArticle(boardDto);
				return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<BoardDto>(HttpStatus.BAD_REQUEST); // 400
			}
		} else {
			return new ResponseEntity<BoardDto>(HttpStatus.UNAUTHORIZED); // 401
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
