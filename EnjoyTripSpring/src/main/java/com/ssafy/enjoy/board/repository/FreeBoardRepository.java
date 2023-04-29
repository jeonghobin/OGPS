package com.ssafy.enjoy.board.repository;

import java.util.List;

import com.ssafy.enjoy.board.dto.FNBoardDto;
import com.ssafy.enjoy.board.dto.FreeCommentDto;


public interface FreeBoardRepository {

	/** 게시판 **/
	public void freeWrite(FNBoardDto fndto) throws Exception;
	public List<FNBoardDto> freeList(int pgno) throws Exception;
	public FNBoardDto freeView(int articleno) throws Exception;
	public void freeDelete(int articleno) throws Exception;
	public void freeUpdate(FNBoardDto fndto) throws Exception;
	public void freeHitUp(int articleno) throws Exception;

	/** 댓글 **/
	public List<FreeCommentDto> commentList(int articleno) throws Exception;
	public FreeCommentDto commetnSelect(int articleno, int commentno) throws Exception;
	public void commentDelete(int articleno, int commentno) throws Exception;
	public void commentUpdate(FreeCommentDto fcdto) throws Exception;
	
}




