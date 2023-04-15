package com.ssafy.model.service;

import com.ssafy.board.model.*;
import com.ssafy.model.dao.*;

public class MemberServiceImpl implements MemberService {

	private static MemberService memberService = new MemberServiceImpl();
	private MemberDao memberDao;

	private MemberServiceImpl() {
		memberDao = MemberDaoImpl.getMemberDao();
	}

	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public boolean idCheck(String id) throws Exception {
		// TODO Auto-generated method stub
		if (!memberDao.idCheck(id)) {
			return true;
		} else
			return false;

	}

	@Override
	public MemberDto login(String userId, String userPwd) throws Exception {
		return memberDao.login(userId, userPwd);
	}

	@Override
	public MemberDto getInfo(String userId) throws Exception {
		return memberDao.getInfo(userId);
	}

	@Override
	public void memberReg(MemberDto m) throws Exception {
		memberDao.memberReg(new MemberDto(m.getId(), m.getName(), m.getPw(), m.getEmail(), m.getDomain()));
	}

	@Override
	public void memberDelete(String id) throws Exception {
		memberDao.memberDelete(id);
	}

	@Override
	public void memberUpdate(MemberDto m) throws Exception {
		memberDao.memberUpdate(m);

	}
}
