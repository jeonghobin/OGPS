package com.ssafy.enjoy.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.enjoy.member.dto.MemberDto;
import com.ssafy.enjoy.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	@Qualifier("MemberServiceMapperImpl")
	MemberService service;
	
	@PostMapping("register")
	public String register(@RequestParam Map<String,String> map) throws Exception {
		MemberDto mdto = new MemberDto();
		mdto.setUser_id(map.get("user-id"));
		mdto.setUser_name(map.get("name"));
		mdto.setUser_password(map.get("password"));
		mdto.setEmail_id(map.get("email"));
		mdto.setEmail_domain(map.get("domain"));
		
		service.memberJoin(mdto);
		
		System.out.println(mdto.toString());
		

		return"redirect:/";
	}
	
	
}
