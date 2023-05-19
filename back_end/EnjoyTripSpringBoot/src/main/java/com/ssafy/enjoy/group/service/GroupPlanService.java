package com.ssafy.enjoy.group.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.group.dto.GroupCommentDto;
import com.ssafy.enjoy.group.dto.GroupDto;
import com.ssafy.enjoy.group.dto.GroupMemberDto;
import com.ssafy.enjoy.group.dto.GroupPlanDto;
import com.ssafy.enjoy.group.dto.GroupPlanPathDto;

public interface GroupPlanService {
	List<GroupPlanDto> getGroupPlan(int groupNo)throws Exception;
	List<GroupCommentDto> getGroupComment(int groupNo)throws Exception;
	List<GroupMemberDto> getGroupMember(int groupNo)throws Exception;
	public GroupPlanDto getDetailPlan(int planNo) throws Exception;
	GroupDto getDetailGroup(int groupNo) throws Exception;
	List<attractionDto> getPlanPath(int planNo) throws Exception;
	List<String> getPlanMemo(int planNo) throws Exception;
	void deletePlan(int planNo) throws Exception;
	void writeGroupPlan(GroupPlanDto gdto, List<Map<String,String>> plist)throws Exception;
	void updateGroupPlan(GroupPlanDto gdto, List<Map<String,String>> plist, int planNo)throws Exception;
	void likeGroupPlan(int planNo, String userId)throws Exception;
}
