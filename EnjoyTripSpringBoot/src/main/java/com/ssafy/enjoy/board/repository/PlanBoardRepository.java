package com.ssafy.enjoy.board.repository;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.board.dto.PlanBoardDto;

public interface PlanBoardRepository {
	
	/** 게시판 **/
	void write(PlanBoardDto pdto) throws Exception;
	int getAuto() throws Exception;
	void writePath(String str, String a) throws Exception;
	List<PlanBoardDto> list(Map<String, Object> map) throws Exception;
	int getTotalArticleCount(Map<String, Object> param) throws Exception;
	void delete(int articleNO) throws Exception;
	void updateHit(int articleNo) throws Exception;
	
	/** view **/
	List<attractionDto> getInfo(String title)throws Exception;
	
}
