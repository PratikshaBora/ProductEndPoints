package com.tka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first")
public class PracticeController {
	
	@GetMapping("/welcome")
	public String hello() {
		return "Hello, welcome to our project.";
	}
}
