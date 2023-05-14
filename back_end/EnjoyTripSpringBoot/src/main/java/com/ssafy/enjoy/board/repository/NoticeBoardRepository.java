package com.ssafy.enjoy.board.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoy.board.dto.FNBoardDto;



public interface NoticeBoardRepository {
	
	
	void write(FNBoardDto boardDto) throws SQLException;
	List<FNBoardDto> list(Map<String, Object> map) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	FNBoardDto getnotice(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modify(FNBoardDto boardDto) throws SQLException;
	void delete(int articleNO) throws SQLException;
	
}
