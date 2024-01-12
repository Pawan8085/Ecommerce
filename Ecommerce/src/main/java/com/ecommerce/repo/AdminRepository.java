package com.ecommerce.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	Optional<Admin> findByEmail(String email);

}
