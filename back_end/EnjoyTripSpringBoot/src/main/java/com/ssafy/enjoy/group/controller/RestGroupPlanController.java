package com.ssafy.enjoy.group.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.group.dto.GroupCommentDto;
import com.ssafy.enjoy.group.dto.GroupMemberDto;
import com.ssafy.enjoy.group.dto.GroupPlanDto;
import com.ssafy.enjoy.group.service.GroupPlanService;

@RestController
@RequestMapping("/api")
public class RestGroupPlanController {

	@Autowired
	@Qualifier("GroupPlanServiceMapperImpl")
	GroupPlanService service;
	
	@GetMapping("/groupplan/{groupNo}")
	public ResponseEntity<Map<String,Object>> getGroup(@PathVariable("groupNo") int groupNo) throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		List<GroupPlanDto> gplist = service.getGroupPlan(groupNo);
		List<GroupCommentDto> gclist = service.getGroupComment(groupNo);
		List<GroupMemberDto> gmlist = service.getGroupMember(groupNo);
		
		Map<String,Object> rsmap = new HashMap<String, Object>();
		rsmap.put("plans", gplist);
		rsmap.put("comments",gclist);
		rsmap.put("members",gmlist);
		rsmap.put("rsmsg", "조회 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		
		
		return entity;
	}
	
	
}
