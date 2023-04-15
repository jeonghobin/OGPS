package com.ssafy.model.service;

import com.ssafy.board.model.*;

public interface MemberService {
	boolean idCheck(String userId) throws Exception;

	MemberDto login(String userId, String userPwd) throws Exception;

	void memberReg(MemberDto memberDto) throws Exception;

	void memberDelete(String id) throws Exception;

	void memberUpdate(MemberDto m) throws Exception;

	MemberDto getInfo(String userId) throws Exception;
}
