package com.ssafy.enjoy.board.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.board.dto.FNBoardDto;
import com.ssafy.enjoy.board.dto.FreeComDto;
import com.ssafy.enjoy.board.service.FreeboardService;
import com.ssafy.enjoy.util.PageNavigation;

@RestController
@RequestMapping(value = "/api")
public class RestFreeboardController {

	@Autowired
	@Qualifier("FreeboardServiceMapperImpl")
	FreeboardService service;

	/** 
	 * 게시판 
	 **/
	@GetMapping("/free")
	public ResponseEntity<Map<String, Object>> list(@RequestParam  Map<String, String> reqmap) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		
		try {
			List<FNBoardDto> list = service.list(reqmap);
			PageNavigation pageNavigation = service.makePageNavigation(reqmap);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "글 조회 성공");
			map.put("posts", list);
			map.put("navigation", pageNavigation);
			map.put("pgno",reqmap.get("pgno"));
			map.put("key",reqmap.get("key"));
			map.put("word",reqmap.get("word"));
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "글 조회 실패");
			map.put("errmsg", e.toString());
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			System.out.println(e.toString());
		}

		return resEntity;
	}

	@PostMapping("/free")
	public ResponseEntity<Map<String, Object>> write(@RequestBody Map<String, String> reqmap) {
		ResponseEntity<Map<String, Object>> resEntity = null;

		try {
			FNBoardDto bdto = new FNBoardDto();
			bdto.setUser_id(reqmap.get("post-user-id"));
			bdto.setContent(reqmap.get("context"));
			bdto.setSubject(reqmap.get("title"));
			
			service.write(bdto);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "글 작성 성공");
			map.put("pgno","1");
			map.put("key","");
			map.put("word","");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (SQLException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "글 작성 실패");
			map.put("errmsg", e.toString());
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}

		return resEntity;
	}

	@DeleteMapping("/free/{articleNo}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable(value = "articleNo") int articleNo) {
		ResponseEntity<Map<String, Object>> reEntity = null;

		try {
			service.delete(articleNo);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "글 삭제 성공");
			map.put("pgno","1");
			map.put("key","");
			map.put("word","");
			reEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (SQLException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "글 삭제 실패");
			map.put("errmsg", e.toString());
			reEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}

		return reEntity;
	}

	@PutMapping("/free")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody Map<String, String> reqmap) {
		ResponseEntity<Map<String, Object>> reEntity = null;

		try {
			FNBoardDto bdto = new FNBoardDto();
			bdto.setArticle_no(Integer.parseInt(reqmap.get("postno")));
			bdto.setContent(reqmap.get("context"));
			bdto.setSubject(reqmap.get("title"));
			
			service.modify(bdto);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "글 수정 성공");
			map.put("pgno","1");
			map.put("key","");
			map.put("word","");
			reEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (SQLException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "글 수정 실패");
			map.put("errmsg", e.toString());
			reEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}

		return reEntity;
	}

	@GetMapping("/free/{articleNo}")
	public ResponseEntity<Map<String, Object>> view(@PathVariable("articleNo") int articleNo) {
		ResponseEntity<Map<String, Object>> reEntity = null;

		try {
			FNBoardDto bdto = service.getnotice(articleNo);
			List<FreeComDto> list = service.comList(articleNo);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "게시판,댓글 목록 가져오기 성공");
			map.put("post", bdto);
			map.put("cposts", list);
			map.put("csize", list.size());
			reEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (SQLException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "게시판,댓글 목록 가져오기 실패");
			map.put("errmsg", e.toString());
			reEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}

		return reEntity;
	}

	/** 
	 * 댓글
	 **/
	@PostMapping("/freecomment")
	public ResponseEntity<Map<String, Object>> comwrite(@RequestBody Map<String, String> reqmap) {
		ResponseEntity<Map<String, Object>> reEntity = null;
		
		try {
			FreeComDto fcdto = new FreeComDto();
			fcdto.setArticle_no(Integer.parseInt(reqmap.get("articleNo")));
			fcdto.setComment(reqmap.get("comment").replace("\r\n", "<br>"));
			fcdto.setUser_id(reqmap.get("user_id"));

			service.comWrite(fcdto);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "댓글 작성 성공");
			reEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (SQLException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "댓글 작성 실패");
			map.put("errmsg", e.toString());
			reEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}

		return reEntity;
	}

	@DeleteMapping("/freecomment/{articleNo}/{commentNo}")
	public ResponseEntity<Map<String, Object>> comdelete(@PathVariable("articleNo") int articleNo, @PathVariable("commentNo") int comment_no) {
		ResponseEntity<Map<String, Object>> reEntity = null;

		try {
			service.comDelete(articleNo, comment_no);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "댓글 삭제 성공");
		} catch (SQLException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "댓글 삭제 실패");
			map.put("errmsg", e.toString());
			reEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}

		return reEntity;
	}

}
