package com.tka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second")
public class SecondController {

	@GetMapping("/")
	public String hello() {
		return "THis is second controller of same project";
	}
	
	@GetMapping("/intro")
	public String intro() {
		return "Spring boot application";
	}
}
