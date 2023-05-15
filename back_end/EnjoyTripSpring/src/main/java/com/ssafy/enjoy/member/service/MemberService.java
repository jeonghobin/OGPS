package com.ssafy.enjoy.member.service;

import com.ssafy.enjoy.member.dto.MemberDto;

public interface MemberService {
	public void memberJoin(MemberDto mdto) throws Exception;
	public MemberDto login(MemberDto mdto) throws Exception;
	public int memberUpdate(MemberDto mdto) throws Exception;
	public void memberDelete(String id) throws Exception;
}