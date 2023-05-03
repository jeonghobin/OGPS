package com.ssafy.enjoy.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.board.dto.PlanBoardDto;
import com.ssafy.enjoy.board.repository.PlanBoardRepository;


@Service("PlanBoardServiceMapperImpl")
public class PlanBoardServiceMapperImpl implements PlanBoardService {
	
	@Autowired
	SqlSession session;
	
	/** 게시판 **/
	@Override
	public void write(PlanBoardDto pdto, List<String> contentId) throws Exception {
		session.getMapper(PlanBoardRepository.class).write(pdto);
		String a = String.valueOf(session.getMapper(PlanBoardRepository.class).getAuto());
		for(String str : contentId) {
			session.getMapper(PlanBoardRepository.class).writePath(str,a);
		}
	}
	
	
	/** view **/
	@Override
	public List<attractionDto> getInfo(String title) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(PlanBoardRepository.class).getInfo(title);
	}

	

}
