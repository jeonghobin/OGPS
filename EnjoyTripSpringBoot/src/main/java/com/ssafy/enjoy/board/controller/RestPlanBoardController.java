package com.ssafy.enjoy.board.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.board.dto.PlanBoardDto;
import com.ssafy.enjoy.board.service.PlanBoardService;

@RestController
@RequestMapping("/api")
public class RestPlanBoardController {
	
	@Autowired
	@Qualifier("PlanBoardServiceMapperImpl")
	PlanBoardService service;
	
	/** 게시판 **/
	@PostMapping("/plan")
	public ResponseEntity<Map<String, Object>> write(@RequestBody Map<String, Object> map) throws Exception {
		PlanBoardDto pdto = new PlanBoardDto();
		pdto.setUser_id((String)map.get("user_id"));
		pdto.setSubject((String)map.get("subject"));
		pdto.setContent((String)map.get("content"));
		pdto.setStart_time((String)map.get("start_time"));
		pdto.setEnd_time((String)map.get("end_time"));
		List<String> contentIds = (List<String>)map.get("contentId");
		service.write(pdto,contentIds);
		
		
		ResponseEntity<Map<String,Object>> entity =null;
		Map<String,Object> rsmap=new HashMap<String, Object>();
		rsmap.put("resmsg", "입력 성공");
		rsmap.put("pgno", "1");
		rsmap.put("key", "");
		rsmap.put("word", "");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		
		return entity;
	}
	
	
	
	/** view **/
	@GetMapping("/plan/{title}")
	public ResponseEntity<Map<String, Object>> search(@PathVariable("title") String title) throws Exception{
		ResponseEntity<Map<String, Object>> entity=null;
		List<attractionDto> attlist = service.getInfo(title);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list",attlist);
		map.put("rsmsg","검색성공");
		entity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
		return entity;
	}
	
}
