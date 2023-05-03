package com.ssafy.enjoy.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.board.dto.PlanBoardDto;
import com.ssafy.enjoy.util.PageNavigation;

public interface PlanBoardService {

	/** 게시판 **/
	void write(PlanBoardDto pbdto, List<String> contentId) throws Exception;
	List<PlanBoardDto> list(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	void delete(int articleNO) throws Exception;
	void updateHit(int articleNo) throws Exception;

	/** view **/
	List<attractionDto> getInfo(String title)throws Exception;
	
}
