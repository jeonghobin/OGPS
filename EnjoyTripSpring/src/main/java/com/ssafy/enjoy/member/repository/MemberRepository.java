package com.ssafy.enjoy.member.repository;



import com.ssafy.enjoy.member.dto.MemberDto;


public interface MemberRepository {
	
	public void memberJoin(MemberDto mdto) throws Exception;
	public boolean login(MemberDto mdto) throws Exception;
	public void memberUpdate(MemberDto mdto) throws Exception;
	public void memberDelete(String id) throws Exception;
	
}
