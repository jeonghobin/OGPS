package com.ssafy.enjoy.group.service;

import java.util.List;

import com.ssafy.enjoy.group.dto.GroupCommentDto;
import com.ssafy.enjoy.group.dto.GroupMemberDto;
import com.ssafy.enjoy.group.dto.GroupPlanDto;

public interface GroupPlanService {
	List<GroupPlanDto> getGroupPlan(int groupNo)throws Exception;
	List<GroupCommentDto> getGroupComment(int groupNo)throws Exception;
	List<GroupMemberDto> getGroupMember(int groupNo)throws Exception;
	
}
