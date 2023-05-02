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
import com.ssafy.enjoy.board.dto.FreeComDto;
import com.ssafy.enjoy.board.service.FreeboardService;
import com.ssafy.enjoy.util.PageNavigation;

@Controller
@RequestMapping("/free")
public class FreeboardController {

	@Autowired
	@Qualifier("FreeboardServiceMapperImpl")
	FreeboardService service;
	
	/** 게시판 **/
	@GetMapping("/list")
	public String list(Model model, @RequestParam Map<String, String> map) throws Exception {
		List<FNBoardDto> list = service.list(map);
		PageNavigation pageNavigation = service.makePageNavigation(map);
		model.addAttribute("posts", list);
		model.addAttribute("navigation", pageNavigation);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		
		
		return"freeboard/information";
	}
	
	@GetMapping("/write")
	public String write() {
		return"freeboard/post";
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
		
		return "redirect:/free/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(value = "article_no") int articleNo,RedirectAttributes ra) throws SQLException {
		service.delete(articleNo);
		
		ra.addAttribute("pgno", "1");
		ra.addAttribute("key", "");
		ra.addAttribute("word", "");
		
		return "redirect:/free/list";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam(value = "article_no") int articleNo, Model model) throws SQLException {
		FNBoardDto bdto = service.getnotice(articleNo);
		model.addAttribute("post",bdto);
		
		return"freeboard/modify";
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
		
		return "redirect:/free/list";
	}
	
	
	@GetMapping("/view")
	public String view(@RequestParam(value = "article_no") int articleNo,Model model) throws SQLException {	
		FNBoardDto bdto=service.getnotice(articleNo);
		model.addAttribute("post",bdto);
		
		List<FreeComDto> list = service.comList(articleNo);
		model.addAttribute("cposts", list);
		model.addAttribute("csize",list.size());
		
		return"freeboard/view";
	}
	
	/** 댓글 **/
	@PostMapping("/comwrite")
	public String comwrite(String user_id, @RequestParam(value = "article_no") int articleNo, String comment) throws SQLException {
		FreeComDto fcdto = new FreeComDto();
		fcdto.setArticle_no(articleNo);
		fcdto.setComment(comment.replace("\r\n","<br>"));
		fcdto.setUser_id(user_id);
		service.comWrite(fcdto);

		return "redirect:/free/view?article_no="+articleNo;
	}
	
	@GetMapping("/comdelete")
	public String comdelete(@RequestParam(value = "article_no") int articleNo, int comment_no) throws SQLException {
		service.comDelete(articleNo, comment_no);

		return "redirect:/free/view?article_no="+articleNo;
	}
	
}
