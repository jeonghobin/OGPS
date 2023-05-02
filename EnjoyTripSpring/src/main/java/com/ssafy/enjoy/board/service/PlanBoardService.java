package com.ssafy.enjoy.board.service;

import java.util.List;

import com.ssafy.enjoy.attraction.dto.attractionDto;

public interface PlanBoardService {
	
	List<attractionDto> getInfo(String title)throws Exception;
	
}
