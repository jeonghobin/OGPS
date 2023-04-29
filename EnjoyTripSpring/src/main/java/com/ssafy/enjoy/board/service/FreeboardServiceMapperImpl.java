package com.ssafy.enjoy.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.board.dto.FNBoardDto;
import com.ssafy.enjoy.board.dto.FreeCommentDto;
import com.ssafy.enjoy.board.repository.FreeBoardRepository;
import com.ssafy.enjoy.member.repository.MemberRepository;

@Service("FreeboardServiceMapperImpl")
public class FreeboardServiceMapperImpl implements FreeboardService{

	@Autowired
	SqlSession session;
	
	/** board **/
	@Override
	public void freeWrite(FNBoardDto fndto) throws Exception {
		session.getMapper(FreeBoardRepository.class).freeWrite(fndto);
	}

	@Override
	public List<FNBoardDto> freeList(int pgno) throws Exception {
		return session.getMapper(FreeBoardRepository.class).freeList(pgno);
	}

	@Override
	public FNBoardDto freeView(int articleno) throws Exception {
		return session.getMapper(FreeBoardRepository.class).freeView(articleno);
	}

	@Override
	public void freeDelete(int articleno) throws Exception {
		session.getMapper(FreeBoardRepository.class).freeDelete(articleno);
	}

	@Override
	public void freeUpdate(FNBoardDto fndto) throws Exception {
		session.getMapper(FreeBoardRepository.class).freeUpdate(fndto);
	}

	@Override
	public void freeHitUp(int articleno) throws Exception {
		session.getMapper(FreeBoardRepository.class).freeHitUp(articleno);
	}

	/** comment **/
	@Override
	public List<FreeCommentDto> commentList(int articleno) throws Exception {
		return session.getMapper(FreeBoardRepository.class).commentList(articleno);
	}

	@Override
	public FreeCommentDto commetnSelect(int articleno, int commentno) throws Exception {
		return session.getMapper(FreeBoardRepository.class).commetnSelect(articleno,commentno);
	}

	@Override
	public void commentDelete(int articleno, int commentno) throws Exception {
		session.getMapper(FreeBoardRepository.class).commentDelete(articleno, commentno);
	}

	@Override
	public void commentUpdate(FreeCommentDto fcdto) throws Exception {
		session.getMapper(FreeBoardRepository.class).commentUpdate(fcdto);
	}

}
