package com.ssafy.enjoy.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String index() {
		return"index";
	}
	@GetMapping("/attractionview")
	public String attraction() {
		return "attraction/attraction";
	}
}
