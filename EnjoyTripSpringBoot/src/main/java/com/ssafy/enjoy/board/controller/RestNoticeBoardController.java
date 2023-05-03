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
import com.ssafy.enjoy.board.service.NoticeBoardService;
import com.ssafy.enjoy.util.PageNavigation;


@RestController
@RequestMapping("/api")
public class RestNoticeBoardController {
	@Autowired
	@Qualifier("NoticeBoardServiceMapperImpl")
	NoticeBoardService service;
	
	
	@GetMapping("/notice")
	public ResponseEntity<Map<String, Object>> list(@RequestParam Map<String, String> map) throws Exception {
		
		ResponseEntity<Map<String,Object>> entity =null;
		Map<String,Object> rsmap=new HashMap<String, Object>();
		List<FNBoardDto> list = service.list(map);
		
		PageNavigation pageNavigation = service.makePageNavigation(map);
		rsmap.put("posts",list);
		rsmap.put("navigation",pageNavigation);
		rsmap.put("pgno",map.get("pgno"));
		rsmap.put("key",map.get("key"));
		rsmap.put("word",map.get("word"));
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		
		return entity;
		
	}
	
	
	@PostMapping("/notice")
	public ResponseEntity<Map<String, Object>> write(@RequestBody Map<String, String> map) throws SQLException {
		FNBoardDto bdto = new FNBoardDto();
		bdto.setUser_id(map.get("post-user-id"));
		bdto.setContent(map.get("context"));
		bdto.setSubject(map.get("title"));
		ResponseEntity<Map<String,Object>> entity =null;
		Map<String,Object> rsmap=new HashMap<String, Object>();
		service.write(bdto);
		rsmap.put("resmsg", "입력 성공");
		rsmap.put("resValue", null);
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	@GetMapping("/notice/{articleno}")
	public ResponseEntity<Map<String, Object>> view(@PathVariable("articleno") int articleNo) throws SQLException {
		
		FNBoardDto bdto=service.getnotice(articleNo);
		ResponseEntity<Map<String,Object>> entity =null;
		Map<String,Object> rsmap=new HashMap<String, Object>();
		rsmap.put("resmsg", "조회 성공");
		rsmap.put("resValue", bdto);
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		
		return entity;
	}
	
	@DeleteMapping("/notice/{articleno}")
	public ResponseEntity<Map<String,Object>> delete(@PathVariable("articleno") int articleNo) throws SQLException {
		service.delete(articleNo);
		
		ResponseEntity<Map<String,Object>> entity =null;
		Map<String,Object> rsmap=new HashMap<String, Object>();
		rsmap.put("resmsg", "삭제 성공");
		rsmap.put("resValue", null);
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		
		return entity;
	}
	

	
	@PutMapping(value = "/notice",  headers = "content-type=application/json")
	public ResponseEntity<Map<String,Object>> modify(@RequestBody Map<String,String>map) throws SQLException {
		FNBoardDto bdto = new FNBoardDto();
		System.out.println(map.get("postno"));
		bdto.setArticle_no(Integer.parseInt(map.get("postno")));
		bdto.setContent(map.get("context"));
		bdto.setSubject(map.get("title"));
		
		service.modify(bdto);
		
		ResponseEntity<Map<String,Object>> entity =null;
		Map<String,Object> rsmap=new HashMap<String, Object>();
		rsmap.put("resmsg", "수정 성공");
		rsmap.put("resValue", null);
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		
		return entity;
	}
}
