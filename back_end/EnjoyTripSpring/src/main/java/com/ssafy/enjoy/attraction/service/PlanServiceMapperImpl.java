package com.ssafy.enjoy.attraction.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.attraction.dto.gugunDto;
import com.ssafy.enjoy.attraction.dto.sidoDto;
import com.ssafy.enjoy.attraction.repository.PlanRepository;

@Service("PlanServiceMapperImpl")
public class PlanServiceMapperImpl implements PlanService {
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<sidoDto> getsido() throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(PlanRepository.class).getsido();
	}

	@Override
	public List<gugunDto> getgugun(int code) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(PlanRepository.class).getgugun(code);
	}

	@Override
	public List<attractionDto> getattraction(Map<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(PlanRepository.class).getattraction(map);
	}
	
	
	
}
