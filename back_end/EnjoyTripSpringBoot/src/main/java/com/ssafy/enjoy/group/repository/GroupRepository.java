package com.ssafy.enjoy.group.repository;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoy.group.dto.GroupDto;

public interface GroupRepository {
	public List<GroupDto> getGroup() throws Exception;
	public List<GroupDto> getMyGroup(String userId) throws Exception;
	public void createGroup(GroupDto gdto) throws Exception;
	public int getAuto() throws Exception;
	public void joinMember(Map<String,String> map) throws Exception;
	public void deleteGroup(int groupNo) throws Exception;
	public void updateGroup(GroupDto gdto) throws Exception;
	
}
