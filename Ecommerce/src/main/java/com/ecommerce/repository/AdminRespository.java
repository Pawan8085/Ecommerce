package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Admin;
import com.ecommerce.model.User;

public interface AdminRespository extends JpaRepository<Admin, Integer>{
	
	Optional<Admin> findByEmail(String mail);

}
