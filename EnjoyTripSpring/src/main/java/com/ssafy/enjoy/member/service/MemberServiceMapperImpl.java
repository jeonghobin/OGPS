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
//		Map<String , String> m = new HashMap();
//		m.put("id",id);
//		m.put("pw",pw);
//		MemberDTO login = session.getMapper(MemberDAO.class).login(m);
		session.getMapper(MemberRepository.class).memberJoin(mdto);

	}

	@Override
	public boolean login(MemberDto mdto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void memberUpdate(MemberDto mdto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void memberDelete(String id) throws Exception {
		// TODO Auto-generated method stub

	}

}
