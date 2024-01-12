package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Admin;
import com.ecommerce.model.User;
import com.ecommerce.repo.AdminRepository;
import com.ecommerce.repo.UserRepository;

@Service
public class MyServiceImpl implements MyService {
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public Admin registerAdmin(Admin admin) {
		
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return adminRepo.save(admin);
		
	}

	@Override
	public User getUserByEmail(String email) {
		
		Optional<User> optUser = userRepo.findByEmail(email);
		if(optUser.isPresent()) {
			
			return optUser.get();
		}
		return null;
	}

	@Override
	public Admin getAdminByEmail(String email) {
		
		Optional<Admin> optAdmin = adminRepo.findByEmail(email);
		if(optAdmin.isPresent()) {
			
			return optAdmin.get();
		}
		return null;
	}

}
