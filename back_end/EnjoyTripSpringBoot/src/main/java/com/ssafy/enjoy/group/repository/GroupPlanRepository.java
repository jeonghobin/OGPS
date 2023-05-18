package com.ssafy.enjoy.group.repository;

import java.util.List;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.group.dto.GroupCommentDto;
import com.ssafy.enjoy.group.dto.GroupDto;
import com.ssafy.enjoy.group.dto.GroupMemberDto;
import com.ssafy.enjoy.group.dto.GroupPlanDto;

public interface GroupPlanRepository {
	public List<GroupPlanDto> getGroupPlan(int groupNo) throws Exception;
	public List<GroupCommentDto> getGroupComment(int groupNo) throws Exception;
	public List<GroupMemberDto> getGroupMember(int groupNo) throws Exception;
	public GroupPlanDto getDetailPlan(int planNo) throws Exception;
	List<attractionDto> getPlanPath(int planNo) throws Exception;
	List<String> getPlanMemo(int planNo) throws Exception;
	void updateHit(String contentId)throws Exception;
	int getAuto() throws Exception;
	void deletePlan(int planNo) throws Exception;
	void writeGroupPlan(GroupPlanDto gdto) throws Exception;
	void updateGroupPlan(String subject,String planNo) throws Exception;
	void writeGroupPlanPath(String a, String contentId,String memo,String seq) throws Exception;
	void deleteGroupPlanPath(String planNo) throws Exception;
	void likeGroupPlan(int planNo) throws Exception;
	GroupDto getDetailGroup(int groupNo) throws Exception;
}
