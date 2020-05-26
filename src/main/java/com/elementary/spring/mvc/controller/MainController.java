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

	@GetMapping("tests/test1")
	public String test1() {
		return "tests/test1";
	}

	@GetMapping("tests/test2")
	public String test2() { return "tests/test2"; }

	@GetMapping("profile/index")
	public String profile() { return "profile/index"; }

}
