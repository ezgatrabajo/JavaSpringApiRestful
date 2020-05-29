package com.elementary.spring.mvc.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/main")
public class MainRestController {
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome"; 
	}

	@GetMapping("test1")
	public String test1(){
		return "API Test 1";
	}

	@GetMapping("test2")
	public String test2(){
		return "API Test 2";
	}


}
