package com.ssafy.enjoy.board.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.enjoy.board.dto.FNBoardDto;
import com.ssafy.enjoy.board.service.NoticeBoardService;
import com.ssafy.enjoy.util.PageNavigation;


@Controller
@RequestMapping("/notice")
public class NoticeBoardController {
	@Autowired
	@Qualifier("NoticeBoardServiceMapperImpl")
	NoticeBoardService service;
	
	
	@GetMapping("/list")
	public String list(Model model, @RequestParam Map<String, String> map) throws Exception {
		
		
		List<FNBoardDto> list = service.list(map);
		PageNavigation pageNavigation = service.makePageNavigation(map);
		model.addAttribute("posts", list);
		model.addAttribute("navigation", pageNavigation);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		
		
		return"noticeboard/information";
		
	}
	
	@GetMapping("/write")
	public String write() {
		return"noticeboard/post";
	}
	
	@PostMapping("/write")
	public String write(@RequestParam Map<String, String> map, RedirectAttributes ra) throws SQLException {
		FNBoardDto bdto = new FNBoardDto();
		bdto.setUser_id(map.get("post-user-id"));
		bdto.setContent(map.get("context"));
		bdto.setSubject(map.get("title"));
		service.write(bdto);
		ra.addAttribute("pgno", "1");
		ra.addAttribute("key", "");
		ra.addAttribute("word", "");
		
		return "redirect:/notice/list";
	}
	@GetMapping("/view")
	public String view(@RequestParam(value = "article_no") int articleNo,Model model) throws SQLException {
		
		FNBoardDto bdto=service.getnotice(articleNo);
		model.addAttribute("post",bdto);
		return"noticeboard/view";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(value = "article_no") int articleNo,RedirectAttributes ra) throws SQLException {
		service.delete(articleNo);
		
		ra.addAttribute("pgno", "1");
		ra.addAttribute("key", "");
		ra.addAttribute("word", "");
		
		return "redirect:/notice/list";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam(value = "article_no") int articleNo, Model model) throws SQLException {
		FNBoardDto bdto = service.getnotice(articleNo);
		model.addAttribute("post",bdto);
		
		return"noticeboard/modify";
	}
	
	@PostMapping("/modify")
	public String modify(@RequestParam Map<String, String> map, RedirectAttributes ra) throws SQLException {
		FNBoardDto bdto = new FNBoardDto();
		bdto.setArticle_no(Integer.parseInt(map.get("postno")));
		bdto.setContent(map.get("context"));
		bdto.setSubject(map.get("title"));
		
		service.modify(bdto);
		
		ra.addAttribute("pgno", "1");
		ra.addAttribute("key", "");
		ra.addAttribute("word", "");
		
		return "redirect:/notice/list";
	}
}
