package com.ssafy.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;

public interface BoardDao {

	void writePost(BoardDto boardDto) throws SQLException;
	
	List<BoardDto> listPost(Map<String, Object> map) throws SQLException;
	
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	
	BoardDto getPost(int articleNo) throws SQLException;
	
	void updateHit(int articleNo) throws SQLException;
	
	void modifyPost(BoardDto boardDto) throws SQLException;
	
	void deletePost(int articleNO) throws SQLException;
	
}
