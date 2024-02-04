package com.poke.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model m) {
		m.addAttribute("msg","훈이가푸시합니다");
		return "index";
	}
}