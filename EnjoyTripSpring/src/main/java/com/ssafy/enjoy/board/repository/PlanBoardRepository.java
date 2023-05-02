package com.ssafy.enjoy.board.repository;

import java.util.List;

import com.ssafy.enjoy.attraction.dto.attractionDto;

public interface PlanBoardRepository {
	
	List<attractionDto> getInfo(String title)throws Exception;
}
