package com.ssafy.enjoy.board.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	/** 게시판 **/
	@GetMapping("/free")
	public ResponseEntity<Map<String, Object>> list(Model model, @RequestBody  Map<String, String> reqmap) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		
		try {
			List<FNBoardDto> list = service.list(reqmap);
			PageNavigation pageNavigation = service.makePageNavigation(reqmap);
			model.addAttribute("posts", list);
			model.addAttribute("navigation", pageNavigation);
			model.addAttribute("pgno", reqmap.get("pgno"));
			model.addAttribute("key", reqmap.get("key"));
			model.addAttribute("word", reqmap.get("word"));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "글 조회 성공");
			map.put("posts", list);
			map.put("navigation", pageNavigation);
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
	public ResponseEntity<Map<String, Object>> write(@RequestBody Map<String, String> reqmap, RedirectAttributes ra) {
		ResponseEntity<Map<String, Object>> resEntity = null;

		try {
			FNBoardDto bdto = new FNBoardDto();
			bdto.setUser_id(reqmap.get("post-user-id"));
			bdto.setContent(reqmap.get("context"));
			bdto.setSubject(reqmap.get("title"));
			service.write(bdto);

			ra.addAttribute("pgno", "1");
			ra.addAttribute("key", "");
			ra.addAttribute("word", "");

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "글 작성 성공");
			map.put("resValue", bdto);
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
	public ResponseEntity<Map<String, Object>> delete(@PathVariable(value = "articleNo") int articleNo,
			RedirectAttributes ra) {
		ResponseEntity<Map<String, Object>> reEntity = null;

		try {
			service.delete(articleNo);

			ra.addAttribute("pgno", "1");
			ra.addAttribute("key", "");
			ra.addAttribute("word", "");

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "글 삭제 성공");
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
	public ResponseEntity<Map<String, Object>> modify(@RequestBody Map<String, String> reqmap, RedirectAttributes ra) {
		ResponseEntity<Map<String, Object>> reEntity = null;

		try {
			FNBoardDto bdto = new FNBoardDto();
			bdto.setArticle_no(Integer.parseInt(reqmap.get("postno")));
			bdto.setContent(reqmap.get("context"));
			bdto.setSubject(reqmap.get("title"));

			service.modify(bdto);

			ra.addAttribute("pgno", "1");
			ra.addAttribute("key", "");
			ra.addAttribute("word", "");

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "글 수정 성공");
			map.put("resValue", bdto);
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

	/** 댓글 **/
	@PostMapping("/freecomment/{userid}/{articleNo}/{comment}")
	public ResponseEntity<Map<String, Object>> comwrite(@PathVariable("userid") String user_id, @PathVariable("articleNo") int articleNo,
			@PathVariable("comment") String comment) {
		ResponseEntity<Map<String, Object>> reEntity = null;
		
		try {
			FreeComDto fcdto = new FreeComDto();
			fcdto.setArticle_no(articleNo);
			fcdto.setComment(comment.replace("\r\n", "<br>"));
			fcdto.setUser_id(user_id);

			service.comWrite(fcdto);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "댓글 작성 성공");
			map.put("resValue", fcdto);
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
