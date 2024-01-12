package com.ecommerce.service;

import com.ecommerce.model.Admin;
import com.ecommerce.model.User;

public interface MyService {
	
	User registerUser(User user);
	Admin registerAdmin(Admin admin);
	
	User getUserByEmail(String email);
	Admin getAdminByEmail(String email);
}
