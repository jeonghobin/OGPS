package com.ssafy.enjoy.attraction.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.attraction.dto.gugunDto;
import com.ssafy.enjoy.attraction.dto.sidoDto;
import com.ssafy.enjoy.attraction.service.PlanService;

@RestController
public class AttractionController {
	
	@Autowired
	@Qualifier("PlanServiceMapperImpl")
	PlanService service;
	
	@GetMapping("/attraction")
	public List<sidoDto> sido() throws Exception{
		List<sidoDto> sidolist = service.getsido(); 
		return sidolist;
	}
	
	@GetMapping("/attraction/{code}")
	public List<gugunDto> gugun(@PathVariable int code) throws Exception{
		List<gugunDto> gugunlist = service.getgugun(code);
		return gugunlist;
	}
	//contentTypeId+"/"+areaCode+"/"+townCode
	@GetMapping("/attraction/{contentType}/{areaCode}/{townCode}")
	public List<attractionDto> attraction(@PathVariable("contentType") int contentType,
			@PathVariable("areaCode") int areaCode,@PathVariable("townCode") int townCode) throws Exception{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("contentType",contentType);
		map.put("areaCode",areaCode);
		map.put("townCode",townCode);
		List<attractionDto> attlist = service.getattraction(map);
		return attlist;
	}
	
	@GetMapping("/attraction/search/{word}")
	public ResponseEntity<Map<String, Object>> getAttraction(@PathVariable("word") String word) throws Exception{
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> rsmap = new HashMap<String, Object>();
		List<attractionDto> attlist=service.getSearchAttraction(word);
		rsmap.put("attractions",attlist);
		rsmap.put("rsmsg","조회 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/attraction/rank")
	public ResponseEntity<Map<String, Object>> getRank() throws Exception{
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> rsmap = new HashMap<String, Object>();
		List<attractionDto> attlist=service.getRank();
		rsmap.put("attractions",attlist);
		rsmap.put("rsmsg","조회 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	} 
	
}
