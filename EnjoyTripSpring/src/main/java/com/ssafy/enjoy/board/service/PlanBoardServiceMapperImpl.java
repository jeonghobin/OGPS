package com.ssafy.enjoy.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.board.repository.PlanBoardRepository;


@Service("PlanBoardServiceMapperImpl")
public class PlanBoardServiceMapperImpl implements PlanBoardService {
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<attractionDto> getInfo(String title) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(PlanBoardRepository.class).getInfo(title);
	}

}
