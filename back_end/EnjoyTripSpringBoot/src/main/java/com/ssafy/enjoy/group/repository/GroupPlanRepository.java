package com.ssafy.enjoy.group.repository;

import java.util.List;

import com.ssafy.enjoy.group.dto.GroupCommentDto;
import com.ssafy.enjoy.group.dto.GroupMemberDto;
import com.ssafy.enjoy.group.dto.GroupPlanDto;

public interface GroupPlanRepository {
	public List<GroupPlanDto> getGroupPlan(int groupNo) throws Exception;
	public List<GroupCommentDto> getGroupComment(int groupNo) throws Exception;
	public List<GroupMemberDto> getGroupMember(int groupNo) throws Exception;
	
}
