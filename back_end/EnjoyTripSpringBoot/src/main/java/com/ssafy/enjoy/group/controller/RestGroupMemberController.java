package com.ssafy.enjoy.group.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.group.service.GroupMemberService;

@RestController
@RequestMapping("/api")
public class RestGroupMemberController {
	
	@Autowired
	@Qualifier("GroupMemberServiceMapperImpl")
	GroupMemberService service;
	
	
	@PostMapping("/groupmember/{groupNo}")
	public ResponseEntity<Map<String,Object>> summit(@PathVariable("groupNo") int groupNo) throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		service.summitJoin(groupNo);
		
		
		return entity;
	}
	
}
