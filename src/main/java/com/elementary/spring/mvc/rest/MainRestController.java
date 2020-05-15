package com.elementary.spring.mvc.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/main")
public class MainRestController {
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome"; 
	}
}
