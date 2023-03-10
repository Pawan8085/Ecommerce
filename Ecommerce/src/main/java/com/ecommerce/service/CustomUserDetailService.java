package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Admin;
import com.ecommerce.model.User;
import com.ecommerce.repository.AdminRespository;
import com.ecommerce.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private AdminRespository adminRespository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optUser = userRepository.findByEmail(username);
		
		if(optUser.isPresent()) {
			
			User user = optUser.get();
			
			return new CustomeUserDetail(user);
		}
		Optional<Admin> optAdmin = adminRespository.findByEmail(username);
		
	    if(optAdmin.isPresent()) {
			Admin admin= optAdmin.get();
			
			return new CustomeAdminDetails(admin);
		}
		
		else throw new UsernameNotFoundException("Invalid User Name!");
		
		
	}

}
