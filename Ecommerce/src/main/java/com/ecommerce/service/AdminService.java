package com.ecommerce.service;

import java.util.List;


import com.ecommerce.exceptions.AdminException;
import com.ecommerce.exceptions.CategoryException;
import com.ecommerce.exceptions.ProductException;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductDetails;

public interface AdminService {
	
	Admin registerAdmin(Admin admin);
	
	
	Category addCategory(Category category)throws AdminException;
	
	List<Category> getAllCategory()throws CategoryException;
	
	Category addProduct(Product product, Integer categoryid)throws CategoryException;
	
	Integer getAllUserCount();
	
	Integer getTotalRevenue();
	
	ProductDetails getProductDetails(Integer productId)throws ProductException;
	
	Product updateProduct(Integer productId, Product product)throws ProductException;
	
	
}
