package com.ssafy.enjoy.attraction.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.attraction.dto.gugunDto;
import com.ssafy.enjoy.attraction.dto.sidoDto;

public interface PlanService {
	
	
	public List<sidoDto> getsido() throws Exception;
	public List<gugunDto> getgugun(int code) throws Exception;
	public List<attractionDto> getattraction(Map<String,Integer> map) throws Exception;
	
}
