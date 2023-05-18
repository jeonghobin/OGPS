package com.ssafy.enjoy.group.service;

import java.util.List;

import com.ssafy.enjoy.group.dto.NoticeDto;

public interface GroupMemberService {
	void submitJoin(int groupNo,String userId)throws Exception;
	List<NoticeDto> getNotice(String userId)throws Exception;
	boolean joinMember(String userId,String groupNo)throws Exception;
	void deleteNotice(String userId,String groupNo) throws Exception;
	void deleteMember(String groupNo,String userId) throws Exception;
}
