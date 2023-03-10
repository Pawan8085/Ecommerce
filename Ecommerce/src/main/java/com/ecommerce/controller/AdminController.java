package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.LoginDTO;
import com.ecommerce.exceptions.AdminException;
import com.ecommerce.exceptions.CategoryException;
import com.ecommerce.exceptions.ProductException;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductDetails;
import com.ecommerce.service.AdminService;

@RestController
@RequestMapping("/ecommerce")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	
	@PostMapping("/admin/register")
	public ResponseEntity<String> registerAdminHandler(@RequestBody Admin admin){
		
		String message = adminService.registerAdmin(admin);
		
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/login")
	public ResponseEntity<String> loginAdminHandler() throws AdminException{
		
		String message = adminService.loginAdmin();
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@GetMapping("/welcome")
	String welcome() {
		return "welcome";
	}
	
	@PostMapping("/admin/category")
	public ResponseEntity<Category> saveCategoryHandler(@RequestBody Category category) throws AdminException{
		
		Category savedCategory = adminService.addCategory(category);
		
		return new ResponseEntity<Category>(savedCategory, HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/category")
	public ResponseEntity<List<Category>> getAllCategoryHandler() throws CategoryException{
		
		List<Category> categories = adminService.getAllCategory();
		
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	
	@PostMapping("/admin/product/{id}")
	public ResponseEntity<Category> addProductHandler(@RequestBody Product product, @PathVariable Integer id) throws CategoryException{
		
		Category category = adminService.addProduct(product, id);
		
		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/totaluser")
	public ResponseEntity<Integer> getTotalUserHandler(){
		
		Integer count = adminService.getAllUserCount();
		
		return new ResponseEntity<Integer>(count, HttpStatus.OK);
	}
	
	@GetMapping("/admin/revenue")
	public ResponseEntity<Integer> getTotalRevenueHandler(){
		
		Integer revenue = adminService.getTotalRevenue();
		
		return new ResponseEntity<Integer>(revenue, HttpStatus.OK);
	}
	
	@GetMapping("/admin/product/detail/{id}")
	public ResponseEntity<ProductDetails> getProductDetailHandler(@PathVariable Integer id) throws ProductException{
		
		ProductDetails  productDetails = adminService.getProductDetails(id);
		
		return new ResponseEntity<ProductDetails>(productDetails, HttpStatus.OK);
	}
	
	

}
