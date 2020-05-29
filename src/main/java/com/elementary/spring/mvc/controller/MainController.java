package com.elementary.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {


	@GetMapping("")
	public String index() {
		return "index";
	}
	

	@GetMapping("home")
	public String home() {
		return "home";
	}

    @GetMapping("login")
    public String login() {
		return "login";
    }


}
