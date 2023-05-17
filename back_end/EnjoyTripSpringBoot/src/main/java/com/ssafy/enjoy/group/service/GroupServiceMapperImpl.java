package com.ssafy.enjoy.group.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.group.dto.GroupDto;
import com.ssafy.enjoy.group.repository.GroupRepository;

@Service("GroupServiceMapperImpl")
public class GroupServiceMapperImpl implements GroupService {
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<GroupDto> getGroup() throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupRepository.class).getGroup();
	}
	@Override
	public List<GroupDto> getMyGroup(String userId) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupRepository.class).getMyGroup(userId);
	}
	@Override
	public void createGroup(GroupDto gdto) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(GroupRepository.class).createGroup(gdto);
		String groupNum  = String.valueOf(session.getMapper(GroupRepository.class).getAuto());
		Map<String,String> map = new HashMap<String, String>();
		map.put("groupNo",groupNum);
		map.put("userId",gdto.getUserId());
		map.put("grade", "1");
		session.getMapper(GroupRepository.class).joinMember(map);
	}
	@Override
	public void deleteGroup(int groupNo) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(GroupRepository.class).deleteGroup(groupNo);
	}
	@Override
	public void updateGroup(GroupDto gdto) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(GroupRepository.class).updateGroup(gdto);
	}
	
	
}
