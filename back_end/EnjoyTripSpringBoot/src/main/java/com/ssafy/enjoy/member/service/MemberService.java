package com.ssafy.enjoy.member.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.enjoy.member.dto.MemberDto;

public interface MemberService {
	public void memberJoin(MemberDto mdto) throws Exception;
	public MemberDto login(MemberDto mdto) throws Exception;
	public int memberUpdate(MemberDto mdto) throws Exception;
	public void memberDelete(String id) throws Exception;
	
	public MemberDto userInfo(String userId) throws Exception;
	public void saveRefreshToken(String userId, String refreshToken) throws Exception;
	public Object getRefreshToken(String userId) throws Exception;
	public void deleRefreshToken(String userId) throws Exception;
}
