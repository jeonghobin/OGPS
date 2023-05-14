package com.ssafy.enjoy.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import com.ssafy.enjoy.board.dto.FNBoardDto;
import com.ssafy.enjoy.util.PageNavigation;


public interface NoticeBoardService {
	
	void write(FNBoardDto boardDto) throws SQLException;
	List<FNBoardDto> list(Map<String, String> map) throws SQLException;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	FNBoardDto getnotice(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modify(FNBoardDto boardDto) throws SQLException;
	void delete(int articleNo) throws SQLException;
	
	
	
}
