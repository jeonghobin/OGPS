package com.ssafy.enjoy.board.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.board.dto.PlanBoardDto;
import com.ssafy.enjoy.board.service.PlanBoardService;
import com.ssafy.enjoy.util.PageNavigation;

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
	
	@GetMapping("/plan")
	public ResponseEntity<Map<String, Object>> list(@RequestParam Map<String, String> reqmap) throws Exception {
		
		List<PlanBoardDto> list = service.list(reqmap);
		PageNavigation pageNavigation = service.makePageNavigation(reqmap);
		
		ResponseEntity<Map<String,Object>> entity =null;
		Map<String,Object> rsmap=new HashMap<String, Object>();
		rsmap.put("resmsg", "리스트 성공");
		rsmap.put("posts",list);
		rsmap.put("navigation",pageNavigation);
		rsmap.put("pgno",reqmap.get("pgno"));
		rsmap.put("key",reqmap.get("key"));
		rsmap.put("word",reqmap.get("word"));
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		
		return entity;
	}
	
	@DeleteMapping("/plan/{articleno}")
	public ResponseEntity<Map<String,Object>> delete(@PathVariable("articleno") int articleNo) throws Exception {
		
		service.delete(articleNo);
		
		ResponseEntity<Map<String,Object>> entity =null;
		Map<String,Object> rsmap=new HashMap<String, Object>();
		rsmap.put("resmsg", "삭제 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		
		return entity;
	}
	
	@GetMapping("/plandetail/{articleno}")
	public ResponseEntity<Map<String,Object>> detail(@PathVariable("articleno") int articleNo) throws Exception{
		
		PlanBoardDto pdto = service.getDetail(articleNo);
		List<attractionDto> pathlist = service.getPaths(articleNo);
		System.out.println(pdto.toString());
		for(attractionDto a: pathlist)
			System.out.println(a.toString());
		ResponseEntity<Map<String,Object>> entity =null;
		Map<String,Object> rsmap=new HashMap<String, Object>();
		rsmap.put("resmsg", "조회 성공");
		rsmap.put("board", pdto);
		rsmap.put("paths", pathlist);
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		
		return entity;
	}
	
	
	/** view **/
	@GetMapping("/plan/{title}")
	public ResponseEntity<Map<String, Object>> search(@PathVariable("title") String title) throws Exception{
		
		List<attractionDto> attlist = service.getInfo(title);
		
		ResponseEntity<Map<String,Object>> entity =null;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list",attlist);
		map.put("rsmsg","검색성공");
		entity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
		return entity;
	}
	
}
