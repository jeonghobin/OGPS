package com.ssafy.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;
import com.ssafy.sample.util.PageNavigation;

public interface BoardService {

	void writePost(BoardDto boardDto) throws SQLException;
	
	List<BoardDto> listPost(Map<String, String> map) throws SQLException;
	
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	
	BoardDto getPost(int articleNo) throws SQLException;
	
	void updateHit(int articleNo) throws SQLException;
	
	void modifyPost(BoardDto boardDto) throws SQLException;
	
	void deletePost(int articleNO) throws SQLException;
	
}
