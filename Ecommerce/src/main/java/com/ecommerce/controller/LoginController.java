package com.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Admin;
import com.ecommerce.model.User;
import com.ecommerce.repo.AdminRepository;
import com.ecommerce.repo.UserRepository;

@RestController
@RequestMapping("/myApp")
public class LoginController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@GetMapping("/user/signIn")
	public ResponseEntity<User> userSignInHandler(Authentication auth){
		
		Optional<User> otpUser = userRepo.findByEmail(auth.getName());
		if(otpUser.isPresent()) {
			
			return new ResponseEntity<User>(otpUser.get(), HttpStatus.OK);
		}
		
		return null;
		
	}
	
	@GetMapping("/admin/signIn")
	public ResponseEntity<Admin> adminSignInHandler(Authentication auth){
		
		Optional<Admin> optAdmin = adminRepo.findByEmail(auth.getName());
		if(optAdmin.isPresent()) {
			
			return new ResponseEntity<Admin>(optAdmin.get(), HttpStatus.OK);
		}
		
		return null;
	}
}
