package com.ssafy.enjoy.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

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
	
	@PostMapping("/register")
	public String register(@RequestParam Map<String,String> map) throws Exception {
		MemberDto mdto = new MemberDto();
		mdto.setUser_id(map.get("user-id"));
		mdto.setUser_name(map.get("name"));
		mdto.setUser_password(map.get("password"));
		mdto.setEmail_id(map.get("email"));
		mdto.setEmail_domain(map.get("domain"));
		
		service.memberJoin(mdto);
		
		return"redirect:/";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam Map<String,String> map, HttpSession session) throws Exception{
		
		MemberDto mdto = new MemberDto();
		mdto.setUser_id(map.get("login-user-id"));
		mdto.setUser_password(map.get("login-password"));
		System.out.println(mdto);
		MemberDto userInfo = service.login(mdto);
		System.out.println(userInfo);
		if(userInfo!=null) {
			session.setAttribute("userInfo", userInfo);
		}
		
		return "redirect:/";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/update")
	public String update(@RequestParam Map<String,String> map,HttpSession session) throws Exception {
		MemberDto mdto = new MemberDto();
		mdto.setUser_id(map.get("user-id"));
		mdto.setUser_name(map.get("name"));
		mdto.setUser_password(map.get("password"));
		mdto.setEmail_id(map.get("email"));
		mdto.setEmail_domain(map.get("domain"));
		
		int a=service.memberUpdate(mdto);
		if(a==1) {
			session.setAttribute("userInfo", mdto);
		}
		return"redirect:/";
	}
	
	@GetMapping("/delete")
	public String delete(String id,HttpSession session) throws Exception {
		service.memberDelete(id);
		session.invalidate();
		return"redirect:/";
	}
}
