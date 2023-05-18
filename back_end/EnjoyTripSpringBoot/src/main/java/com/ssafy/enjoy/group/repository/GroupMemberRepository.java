package com.ssafy.enjoy.group.repository;

import java.util.List;

import com.ssafy.enjoy.group.dto.NoticeDto;

public interface GroupMemberRepository {
	String getOwner(int groupNo) throws Exception; 
	void submitJoin(String groupNo,String owner, String userId)throws Exception;
	List<NoticeDto> getNotice(String userId)throws Exception;
	void deleteNotice(String groupNo,String userId)throws Exception;
	int getTotalMember(String groupNo)throws Exception;
	void deleteMember(String groupNo,String userId)throws Exception;
}
