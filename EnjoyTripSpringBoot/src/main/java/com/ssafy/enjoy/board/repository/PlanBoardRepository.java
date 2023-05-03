package com.ssafy.enjoy.board.repository;

import java.util.List;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.board.dto.PlanBoardDto;

public interface PlanBoardRepository {
	
	/** 게시판 **/
	void write(PlanBoardDto pdto) throws Exception;
	int getAuto() throws Exception;
	void writePath(String str, String a) throws Exception;
	
	/** view **/
	List<attractionDto> getInfo(String title)throws Exception;
	
}
