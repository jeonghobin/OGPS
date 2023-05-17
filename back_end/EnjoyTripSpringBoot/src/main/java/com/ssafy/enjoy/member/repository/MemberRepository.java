package com.ssafy.enjoy.member.repository;



import java.sql.SQLException;
import java.util.Map;

import com.ssafy.enjoy.member.dto.MemberDto;


public interface MemberRepository {
	
	public void memberJoin(MemberDto mdto) throws Exception;
	public MemberDto login(MemberDto mdto) throws Exception;
	public int memberUpdate(MemberDto mdto) throws Exception;
	public void memberDelete(String id) throws Exception;
	
	public MemberDto userInfo(String userId) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userId) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
}
