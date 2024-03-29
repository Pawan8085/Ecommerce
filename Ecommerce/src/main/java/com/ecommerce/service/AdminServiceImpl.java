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


import com.ecommerce.exceptions.AdminException;
import com.ecommerce.exceptions.CategoryException;
import com.ecommerce.exceptions.ProductException;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductDetails;
import com.ecommerce.repo.AdminRepository;
import com.ecommerce.repo.CategoryRepository;
import com.ecommerce.repo.ProductDetailsRespository;
import com.ecommerce.repo.ProductRepository;
import com.ecommerce.repo.UserRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
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
	public Admin registerAdmin(Admin admin) {
		
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
	   return adminRepository.save(admin);
	    
	   
	}

	
	@Override
	public Category addCategory(Category category) throws AdminException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Optional<Admin> optAdmin = adminRepository.findByEmail(userName);
		
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
	public Category addProduct(Product product, Integer categoryid) throws CategoryException {
	
		
		Optional<Category> optCat = categoryRepository.findById(categoryid);
		
		if(optCat.isPresent()) {
			
			Category category = optCat.get();
			category.getProducts().add(product);
			product.setCategory(category);
			productRepository.save(product);
			
			ProductDetails productdetails = new ProductDetails();
			productdetails.setProductId(product.getProductId());
			productdetails.setTotalProduct(product.getProductStock());
			productdetails.setSold(0);
			productdetails.setTotalRevenue(0);
			
			productDetialDetailsRespository.save(productdetails);
			
			
			
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
		
		Optional<ProductDetails> optProductDetail = productDetialDetailsRespository.findByProductId(productId);
		
		if(optProductDetail.isPresent()) {
			
			return optProductDetail.get();
		}
		
		throw new ProductException("Invalid product id "+productId);
	}


	@Override
	public Product updateProduct(Integer productId, Product product) throws ProductException {
		
		Optional<Product> optProduct = productRepository.findById(productId);
		
		if(optProduct.isPresent()) {
			
			
			product.setProductId(productId);
			productRepository.save(product);
			
			return product;
		}
		
		throw new ProductException("Invalid Product id!");
		
	}

	

}
