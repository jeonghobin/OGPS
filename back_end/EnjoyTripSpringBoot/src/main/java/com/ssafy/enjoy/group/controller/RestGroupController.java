package com.ssafy.enjoy.group.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.group.dto.GroupDto;
import com.ssafy.enjoy.group.service.GroupService;

@RestController
@RequestMapping("/api")
public class RestGroupController {
	
	@Autowired
	@Qualifier("GroupServiceMapperImpl")
	GroupService service;
	
	@GetMapping("/group")
	public ResponseEntity<Map<String,Object>> getGroup() throws Exception{
		ResponseEntity<Map<String,Object>> entity =null;
		Map<String, Object> rsmap = new HashMap<String, Object>();
		List<GroupDto> gdto = service.getGroup();
		rsmap.put("data", gdto);
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/group/{userId}")
	public ResponseEntity<Map<String,Object>> getmyGroup(@PathVariable("userId") String userId)throws Exception{
		ResponseEntity<Map<String,Object>> entity =null;
		Map<String, Object> rsmap = new HashMap<String, Object>();
		List<GroupDto> gdto = service.getMyGroup(userId);
		rsmap.put("data", gdto);
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/group")
	public ResponseEntity<Map<String,Object>> creategroup(@RequestBody Map<String,Object> map) throws Exception{
		ResponseEntity<Map<String,Object>> entity =null;
		GroupDto gdto = new GroupDto();
		gdto.setUserId((String)map.get("userId"));
		gdto.setSubject((String)map.get("subject"));
		gdto.setStartTime((String)map.get("startTime"));
		gdto.setEndTime((String)map.get("endTime"));
		gdto.setMemberCnt(Integer.parseInt((String)map.get("memberCnt")));
		service.createGroup(gdto);
		Map<String,Object> rsmap=new HashMap<String, Object>();
		rsmap.put("resmsg", "입력 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@DeleteMapping("/group/{groupNo}")
	public ResponseEntity<Map<String,Object>> deletegroup(@PathVariable("groupNo") String groupNo) throws Exception{
		ResponseEntity<Map<String,Object>> entity =null;
		int no = Integer.parseInt(groupNo);
		service.deleteGroup(no);
		Map<String,Object> rsmap=new HashMap<String, Object>();
		rsmap.put("resmsg", "삭제 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@PutMapping("/group")
	public ResponseEntity<Map<String,Object>> updategroup(@RequestBody Map<String,Object> map) throws Exception{
		ResponseEntity<Map<String,Object>> entity =null;
		GroupDto gdto = new GroupDto();
		gdto.setGroupNo(Integer.parseInt((String)map.get("groupNo")));
		gdto.setSubject((String)map.get("subject"));
		gdto.setStartTime((String)map.get("startTime"));
		gdto.setEndTime((String)map.get("endTime"));
		gdto.setMemberCnt(Integer.parseInt((String)map.get("memberCnt")));
		service.updateGroup(gdto);
		Map<String,Object> rsmap=new HashMap<String, Object>();
		rsmap.put("resmsg", "수정 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
}
