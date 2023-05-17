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
	public MemberDto login(MemberDto memberDto) throws Exception {
		if (memberDto.getUserId() == null || memberDto.getUserPassword() == null)
			return null;
		return session.getMapper(MemberRepository.class).login(memberDto);
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

	@Override
	public MemberDto userInfo(String userId) throws Exception {
		return session.getMapper(MemberRepository.class).userInfo(userId);
	}

	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		session.getMapper(MemberRepository.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return session.getMapper(MemberRepository.class).getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		session.getMapper(MemberRepository.class).deleteRefreshToken(map);
	}

}
