package com.ecommerce.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.model.ProductDetails;

public interface ProductDetailsRespository extends JpaRepository<ProductDetails, Integer>{
	
	Optional<ProductDetails> findByProductId(Integer id);
	
	 @Query("SELECT SUM(p.totalRevenue) FROM ProductDetails p")
	Integer totalRevenue();
	

}
