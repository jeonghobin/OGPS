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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.group.dto.NoticeDto;
import com.ssafy.enjoy.group.service.GroupMemberService;

@RestController
@RequestMapping("/api")
public class RestGroupMemberController {
	
	@Autowired
	@Qualifier("GroupMemberServiceMapperImpl")
	GroupMemberService service;
	
	
	@PostMapping("/groupmember/{groupNo}/{userId}")
	public ResponseEntity<Map<String,Object>> submit(@PathVariable("groupNo") int groupNo
			,@PathVariable("userId") String userId) throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		service.submitJoin(groupNo,userId); 
		Map<String, Object> rsmap = new HashMap<String, Object>();
		rsmap.put("rsmsg","참가요청 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@DeleteMapping("/groupmember/{groupNo}/{userId}")
	public ResponseEntity<Map<String,Object>> deleteMember(@PathVariable("groupNo") int groupNo
			,@PathVariable("userId") String userId) throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		service.deleteMember(String.valueOf(groupNo),String.valueOf(userId)); 
		Map<String, Object> rsmap = new HashMap<String, Object>();
		rsmap.put("rsmsg","맴버 삭제 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	
	@GetMapping("/groupmember/{userId}")
	public ResponseEntity<Map<String,Object>> getNotice(@PathVariable("userId") String userId)throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		List<NoticeDto> nlist = service.getNotice(userId);
		Map<String, Object> rsmap = new HashMap<String, Object>();
		rsmap.put("rsmsg","notice조회 성공");
		rsmap.put("notices", nlist);
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/groupmember")
	public ResponseEntity<Map<String,Object>> joinMember(@RequestBody Map<String,Object>map)throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		String userId = (String)map.get("userId");
		String groupNo = (String)map.get("groupNo");
		boolean flag=service.joinMember(userId,groupNo);
		Map<String, Object> rsmap = new HashMap<String, Object>();
		if(flag)
			rsmap.put("rsmsg", "승인 완료");
		else
			rsmap.put("rsmsg", "승인 거절 수용 인원 터짐");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@DeleteMapping("/groupmember")
	public ResponseEntity<Map<String,Object>> deleteNotice(@RequestBody Map<String,Object>map)throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		String userId = (String)map.get("userId");
		String groupNo = (String)map.get("groupNo");
		service.deleteNotice(userId,groupNo);
		Map<String, Object> rsmap = new HashMap<String, Object>();
		rsmap.put("rsmsg", "승인 거절 완료");
		
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
}
