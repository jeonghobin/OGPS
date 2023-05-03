package com.ssafy.enjoy.board.service;

import java.util.List;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.board.dto.PlanBoardDto;

public interface PlanBoardService {

	/** 게시판 **/
	void write(PlanBoardDto pbdto, List<String> contentId) throws Exception;
	

	/** view **/
	List<attractionDto> getInfo(String title)throws Exception;
	
}
