package com.ssafy.enjoy.group.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.group.dto.GroupCommentDto;
import com.ssafy.enjoy.group.dto.GroupMemberDto;
import com.ssafy.enjoy.group.dto.GroupPlanDto;
import com.ssafy.enjoy.group.repository.GroupPlanRepository;

@Service("GroupPlanServiceMapperImpl")
public class GroupPlanServiceMapperImpl implements GroupPlanService {
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<GroupPlanDto> getGroupPlan(int groupNo) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupPlanRepository.class).getGroupPlan(groupNo);
	}

	@Override
	public List<GroupCommentDto> getGroupComment(int groupNo) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupPlanRepository.class).getGroupComment(groupNo);
	}

	@Override
	public List<GroupMemberDto> getGroupMember(int groupNo) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupPlanRepository.class).getGroupMember(groupNo);
	}

}
