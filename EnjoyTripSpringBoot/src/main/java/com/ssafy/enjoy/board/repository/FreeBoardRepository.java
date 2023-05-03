package com.ssafy.enjoy.board.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoy.board.dto.FNBoardDto;
import com.ssafy.enjoy.board.dto.FreeComDto;


public interface FreeBoardRepository {

	/** 게시판 **/
	void write(FNBoardDto boardDto) throws SQLException;
	List<FNBoardDto> list(Map<String, Object> map) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	FNBoardDto getnotice(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	void modify(FNBoardDto boardDto) throws SQLException;
	void delete(int articleNO) throws SQLException;

	/** 댓글 **/
	public List<FreeComDto> comList(int articleno) throws SQLException;
	public FreeComDto comOne(int articleno, int commentno) throws SQLException;
	public void comDelete( int commentno) throws SQLException;
	public void comModify(FreeComDto fcdto) throws SQLException;
	public void comWrite(FreeComDto fcdto) throws SQLException;
	
}




