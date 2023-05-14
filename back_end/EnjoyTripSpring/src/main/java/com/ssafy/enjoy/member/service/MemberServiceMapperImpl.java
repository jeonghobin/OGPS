package com.ssafy.enjoy.member.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.member.dto.MemberDto;
import com.ssafy.enjoy.member.repository.MemberRepository;



@Service("MemberServiceMapperImpl")
public class MemberServiceMapperImpl implements MemberService {
	
	@Autowired
	SqlSession session;
	
	@Override
	public void memberJoin(MemberDto mdto) throws Exception {

		session.getMapper(MemberRepository.class).memberJoin(mdto);

	}

	@Override
	public MemberDto login(MemberDto mdto) throws Exception {
		
		return session.getMapper(MemberRepository.class).login(mdto);
	}

	@Override
	public int memberUpdate(MemberDto mdto) throws Exception {
		return session.getMapper(MemberRepository.class).memberUpdate(mdto);

	}

	@Override
	public void memberDelete(String id) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(MemberRepository.class).memberDelete(id);
	}

}
