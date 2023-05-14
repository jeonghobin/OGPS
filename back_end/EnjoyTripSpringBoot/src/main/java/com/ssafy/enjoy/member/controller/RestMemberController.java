package com.ssafy.enjoy.member.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.member.dto.MemberDto;
import com.ssafy.enjoy.member.service.MemberService;

@RestController
@RequestMapping(value = "/api")
public class RestMemberController {

	@Autowired
	@Qualifier("MemberServiceMapperImpl")
	MemberService service;

	@PostMapping("/member")
	public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> reqmap) throws Exception {
		ResponseEntity<Map<String, Object>> resEntity = null;

		MemberDto mdto = new MemberDto();
		mdto.setUser_id(reqmap.get("user-id"));
		mdto.setUser_name(reqmap.get("name"));
		mdto.setUser_password(reqmap.get("password"));
		mdto.setEmail_id(reqmap.get("email"));
		mdto.setEmail_domain(reqmap.get("domain"));

		service.memberJoin(mdto);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resmsg", "회원 등록 성공");
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		return resEntity;
	}

	
	@PutMapping("/member")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Map<String, String> reqmap) throws Exception {
		ResponseEntity<Map<String, Object>> resEntity = null;
		
		MemberDto mdto = new MemberDto();
		mdto.setUser_id(reqmap.get("user-id"));
		mdto.setUser_name(reqmap.get("name"));
		mdto.setUser_password(reqmap.get("password"));
		mdto.setEmail_id(reqmap.get("email"));
		mdto.setEmail_domain(reqmap.get("domain"));

		int a = service.memberUpdate(mdto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resmsg", "회원정보 수정 성공");
		map.put("status", a);
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		
		return resEntity;
	}

	@DeleteMapping("/member/{user-id}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable("user-id") String userId) throws Exception {
		ResponseEntity<Map<String, Object>> resEntity = null;
		
		service.memberDelete(userId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resmsg", "회원 탈퇴 성공");
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		return resEntity;
	}
	
	
	@PostMapping("/login/{user-id}/{password}")
	public ResponseEntity<Map<String, Object>> login(@PathVariable("user-id") String userId, @PathVariable("password") String userPw) throws Exception{
		ResponseEntity<Map<String, Object>> resEntity = null;
		
		MemberDto mdto = new MemberDto();
		mdto.setUser_id(userId);
		mdto.setUser_password(userPw);
		
		MemberDto userInfo = service.login(mdto);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resmsg", "로그인 성공");
		map.put("userInfo", userInfo);
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		return resEntity;
	}
}
