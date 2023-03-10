package com.ecommerce.service;

import java.util.List;

import com.ecommerce.exceptions.CartException;
import com.ecommerce.exceptions.ProductException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Comment;
import com.ecommerce.model.Orders;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;

public interface UserService {
	
	User registerUser(User user);
	
	String buyProduct(Integer productId, Integer quantity)throws ProductException;
	
	String addToCart(Integer productId, Integer quantity)throws ProductException, CartException;
	
	String byfromCart(Integer cartId)throws CartException, ProductException;
	
	List<Cart> getAllCartProducts();
	
	List<Orders> getAllOrderdProduct();
	
	List<Product> getAllProducts();
	
	List<Product> searchProducts(String keyword);
	
	List<Product> searchProductAndFilterByRating(String keyworad, Integer rating);
	
	List<Product> searchProductAndFilterByPrice(String keyword, Integer minPrice, Integer maxPrice);
	
	Product addRatingAndComment(Integer productid, Comment comment)throws ProductException;
	
	
}
