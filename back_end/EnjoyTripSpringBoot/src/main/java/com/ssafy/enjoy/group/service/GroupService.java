package com.ssafy.enjoy.group.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoy.group.dto.GroupDto;
import com.ssafy.enjoy.group.dto.GroupPlanDto;

public interface GroupService {
	public List<GroupDto> getGroup()throws Exception;
	public List<GroupDto> getMyGroup(String userId)throws Exception;
	public void createGroup(GroupDto gdto)throws Exception;
	public void deleteGroup(int groupNo) throws Exception;
	public void updateGroup(GroupDto gdto) throws Exception;
}
