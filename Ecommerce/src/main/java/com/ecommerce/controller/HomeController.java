package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	public String welcome() {
		
		return "Welcome to this application!";
	}

}
