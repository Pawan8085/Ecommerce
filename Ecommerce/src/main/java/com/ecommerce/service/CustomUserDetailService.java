package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Admin;
import com.ecommerce.model.User;
import com.ecommerce.repo.AdminRepository;
import com.ecommerce.repo.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AdminRepository adminRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optUser  = userRepo.findByEmail(username);
		if(optUser.isPresent()) {
			
			User user = optUser.get();
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getRole()));
			
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
			
			
		}
		
		
		Optional<Admin> optAdmin = adminRepo.findByEmail(username);
		if(optAdmin.isPresent()) {
			
			
			Admin admin = optAdmin.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(admin.getRole()));
			
			return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(), authorities);
			
			
			
		}
		throw new UsernameNotFoundException("Invalid user name : "+username);
	}

}
