package com.ssafy.enjoy.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.board.service.PlanBoardService;

@RestController
@RequestMapping("/api")
public class RestPlanBoardController {
	
	@Autowired
	@Qualifier("PlanBoardServiceMapperImpl")
	PlanBoardService service;
	
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
