package com.ssafy.enjoy.group.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.group.dto.GroupCommentDto;
import com.ssafy.enjoy.group.dto.GroupDto;
import com.ssafy.enjoy.group.dto.GroupMemberDto;
import com.ssafy.enjoy.group.dto.GroupPlanDto;
import com.ssafy.enjoy.group.dto.GroupPlanPathDto;
import com.ssafy.enjoy.group.repository.GroupPlanRepository;

@Service("GroupPlanServiceMapperImpl")
public class GroupPlanServiceMapperImpl implements GroupPlanService {
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<GroupPlanDto> getGroupPlan(int groupNo) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupPlanRepository.class).getGroupPlan(groupNo);
	}

	@Override
	public List<GroupCommentDto> getGroupComment(int groupNo) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupPlanRepository.class).getGroupComment(groupNo);
	}

	@Override
	public List<GroupMemberDto> getGroupMember(int groupNo) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupPlanRepository.class).getGroupMember(groupNo);
	}

	@Override
	public GroupPlanDto getDetailPlan(int planNo) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupPlanRepository.class).getDetailPlan(planNo);
	}

	@Override
	public List<attractionDto> getPlanPath(int planNo) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupPlanRepository.class).getPlanPath(planNo);
	}
	@Override
	public List<String> getPlanMemo(int planNo) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupPlanRepository.class).getPlanMemo(planNo);
	}
	
	@Override
	public void deletePlan(int planNo) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(GroupPlanRepository.class).deletePlan(planNo);
	}

	@Override
	@Transactional
	public void writeGroupPlan(GroupPlanDto gdto, List<Map<String,String>> plist) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(GroupPlanRepository.class).writeGroupPlan(gdto);
		String a = String.valueOf(session.getMapper(GroupPlanRepository.class).getAuto());
		int i=1;
		for(Map<String,String> path: plist) {
			String seq = String.valueOf(i);
			session.getMapper(GroupPlanRepository.class).writeGroupPlanPath(a,path.get("contentId"),path.get("memo"),seq);
			session.getMapper(GroupPlanRepository.class).updateHit(path.get("contentId"));
			i++;
		}
	}

	@Override
	@Transactional
	public void updateGroupPlan(GroupPlanDto gdto, List<Map<String, String>> plist, int planNo) throws Exception {
		// TODO Auto-generated method stub
		String planNostr = String.valueOf(planNo);
		session.getMapper(GroupPlanRepository.class).updateGroupPlan(gdto.getSubject(),planNostr);
		int i=1;
		session.getMapper(GroupPlanRepository.class).deleteGroupPlanPath(planNostr);
		for(Map<String,String> path: plist) {
			String seq = String.valueOf(i); 
			session.getMapper(GroupPlanRepository.class).writeGroupPlanPath(planNostr,path.get("contentId"),path.get("memo"),seq);
			session.getMapper(GroupPlanRepository.class).updateHit(path.get("contentId"));
			i++;
		}
	}

	@Override
	@Transactional
	public void likeGroupPlan(int planNo,String userId) throws Exception {
		// TODO Auto-generated method stub
		int i = session.getMapper(GroupPlanRepository.class).getHeart(String.valueOf(planNo),userId);
		if(i==1) {
			session.getMapper(GroupPlanRepository.class).deleteHeart(String.valueOf(planNo),userId);
			session.getMapper(GroupPlanRepository.class).downHeart(planNo);
		}else {
			session.getMapper(GroupPlanRepository.class).likeGroupPlan(String.valueOf(planNo),userId);	
			session.getMapper(GroupPlanRepository.class).updateHeart(planNo);
		}
	}
	@Override
	public GroupDto getDetailGroup(int groupNo) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(GroupPlanRepository.class).getDetailGroup(groupNo);
	}

	

}
