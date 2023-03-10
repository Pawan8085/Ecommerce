package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.LoginDTO;
import com.ecommerce.exceptions.AdminException;
import com.ecommerce.exceptions.CategoryException;
import com.ecommerce.exceptions.ProductException;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductDetails;
import com.ecommerce.repository.AdminRespository;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductDetailsRespository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRespository adminRespository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductDetailsRespository productDetialDetailsRespository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public String registerAdmin(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		
	    adminRespository.save(admin);
	    
	    return "You have been Registerd!";
	}

	
	@Override
	public Category addCategory(Category category) throws AdminException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Optional<Admin> optAdmin = adminRespository.findByEmail(userName);
		
		if(optAdmin.isPresent()) {
			
			Admin admin = optAdmin.get();
			admin.getCategories().add(category);
			category.setAdmin(admin);
			
			return categoryRepository.save(category);
			
			
		}
		
		throw new AdminException("Normal user can't access it!");
		
	}

	@Override
	public List<Category> getAllCategory() throws CategoryException {
		
		List<Category> categories =categoryRepository.findAll();
		if(categories.size()!=0) {
			return categories;
		}
		else throw new CategoryException("Category not found!");
	}

	@Override
	public String loginAdmin() throws AdminException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Optional<Admin> optAdmin = adminRespository.findByEmail(userName);
		
		if(optAdmin.isPresent()) {
			
			Admin admin = optAdmin.get();
			
			return "Hi "+admin.getFirstName()+" "+admin.getLastName();
		}
		
		throw new AdminException("Invalid admin credentials!");
	}


	@Override
	public Category addProduct(Product product, Integer categoryid) throws CategoryException {
	
		
		Optional<Category> optCat = categoryRepository.findById(categoryid);
		
		if(optCat.isPresent()) {
			
			Category category = optCat.get();
			category.getProducts().add(product);
			product.setCategory(category);
			productRepository.save(product);
			
			return category;
				
		}
		throw new CategoryException("Invalid category id "+categoryid);
	}


	@Override
	public Integer getAllUserCount() {
		return (int) userRepository.count();
	}


	@Override
	public Integer getTotalRevenue() {
		
		return productDetialDetailsRespository.totalRevenue();
	}


	@Override
	public ProductDetails getProductDetails(Integer productId) throws ProductException {
		
		Optional<ProductDetails> optProductDetail = productDetialDetailsRespository.findById(productId);
		
		if(optProductDetail.isPresent()) {
			
			return optProductDetail.get();
		}
		
		throw new ProductException("Invalid product id "+productId);
	}

	

}
