package com.ssafy.enjoy.board.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.enjoy.board.dto.FNBoardDto;
import com.ssafy.enjoy.board.service.FreeboardService;
import com.ssafy.enjoy.util.QuickSorter;

@Controller
@RequestMapping("/freeboard")
public class FreeboardController {

	@Autowired
	@Qualifier("FreeboardServiceMapperImpl")
	FreeboardService service;
	

	@GetMapping("/list")
	public String list(@RequestParam Map<String, String> map, HttpSession session) throws Exception {

		
		List<FNBoardDto> list = QuickSorter.quickSort(service.freeList(0));
//		List<FNBoardDto> list = QuickSorter.quickSort(service.freeList(Integer.parseInt(map.get("pgno"))));
		session.setAttribute("posts", list);
		

		//나중에 페이징..
//		PageNavigation pageNavigation = service.makePageNavigation(map);
//		session.setAttribute("navigation", pageNavigation);

		return "/freeboard/information";
	}
	
	@GetMapping("/write")
	public String write(HttpSession session) throws Exception{
		
		return "/freeboard/write";
	}
	
	@PostMapping("/write")
	public String write(@RequestParam Map<String, String> map) throws Exception{

		FNBoardDto fndto = new FNBoardDto();
		fndto.setArticle_no(Integer.parseInt(map.get("")));
		fndto.setSubject(map.get(""));
		fndto.setUser_id(map.get(""));
		fndto.setRegister_time(map.get(""));
		fndto.setHit(Integer.parseInt(map.get("")));
		
		service.freeWrite(fndto);
		
		return "/freeboard/information";
	}
	
	
}
