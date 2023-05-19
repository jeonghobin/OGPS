package com.ssafy.enjoy.group.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoy.group.dto.GroupDto;
import com.ssafy.enjoy.group.dto.NoticeDto;
import com.ssafy.enjoy.group.repository.GroupMemberRepository;
import com.ssafy.enjoy.group.repository.GroupPlanRepository;
import com.ssafy.enjoy.group.repository.GroupRepository;

@Service("GroupMemberServiceMapperImpl")
public class GroupMemberServiceMapperImpl implements GroupMemberService {

	@Autowired
	SqlSession session;
	
	@Override
	public void submitJoin(int groupNo, String userId) throws Exception {
		// TODO Auto-generated method stub
		String owner = session.getMapper(GroupMemberRepository.class).getOwner(groupNo);
		String groupNostr = String.valueOf(groupNo);
		session.getMapper(GroupMemberRepository.class).submitJoin(groupNostr,owner,userId);
	}

	@Override
	public List<NoticeDto> getNotice(String userId) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupMemberRepository.class).getNotice(userId);
	}

	@Override
	@Transactional
	public boolean joinMember(String userId, String groupNo) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		map.put("groupNo",groupNo);
		map.put("userId",userId);
		map.put("grade", "0");
		GroupDto gdto = session.getMapper(GroupPlanRepository.class).getDetailGroup(Integer.parseInt(groupNo));
		int membercnt = gdto.getMemberCnt();
		int totalmember = session.getMapper(GroupMemberRepository.class).getTotalMember(groupNo);
		if(membercnt>totalmember) {
			session.getMapper(GroupRepository.class).joinMember(map);
			session.getMapper(GroupMemberRepository.class).deleteNotice(groupNo,userId);
			return true;
		}else {
			session.getMapper(GroupMemberRepository.class).deleteNotice(groupNo,userId);
			return false;
		}
	}

	@Override
	public void deleteNotice(String userId, String groupNo) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(GroupMemberRepository.class).deleteNotice(groupNo,userId);
	}

	@Override
	public void deleteMember(String groupNo, String userId) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(GroupMemberRepository.class).deleteMember(groupNo,userId);
	}
	
}
