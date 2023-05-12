package com.ssafy.enjoy.attraction.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
}
