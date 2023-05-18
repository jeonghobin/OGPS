package com.ssafy.enjoy.group.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.group.repository.GroupMemberRepository;

@Service("GroupMemberServiceMapperImpl")
public class GroupMemberServiceMapperImpl implements GroupMemberService {

	@Autowired
	SqlSession session;
	
	@Override
	public void summitJoin(int groupNo) throws Exception {
		// TODO Auto-generated method stub
		String owner = session.getMapper(GroupMemberRepository.class).getOwner(groupNo);
//		session.getMapper(GroupMemberRepository.class).summitJoin()
	}
	
}
