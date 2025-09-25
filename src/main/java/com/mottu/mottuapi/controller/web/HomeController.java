package com.mottu.mottuapi.controller.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Authentication authentication, Model model) {
		model.addAttribute("usuario", authentication != null ? authentication.getName() : "");
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
} 