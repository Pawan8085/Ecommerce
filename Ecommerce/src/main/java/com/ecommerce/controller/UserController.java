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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exceptions.CartException;
import com.ecommerce.exceptions.ProductException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Comment;
import com.ecommerce.model.Orders;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.service.UserService;

@RestController
@RequestMapping("/ecommerce")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/user/register")
	public ResponseEntity<User> registerUser(@RequestBody User user){
		
		User savedUser = userService.registerUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/product/{productid}/{quantity}")
	public ResponseEntity<String> byProductHandler(@PathVariable Integer productid, @PathVariable Integer quantity) throws ProductException{
		
		String msg = userService.buyProduct(productid, quantity);
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PostMapping("/user/cart/{productid}/{quantity}")
	public ResponseEntity<String> addToCartHandler(@PathVariable Integer productid, @PathVariable Integer quantity ) throws CartException, ProductException{
		
		String message = userService.addToCart(productid, quantity);
		
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/cart/{cartid}")
	public ResponseEntity<String> buyFromCartHandler(@PathVariable Integer cartid) throws CartException, ProductException{
		
		String message = userService.byfromCart(cartid);
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@GetMapping("/user/cart")
	public ResponseEntity<List<Cart>> getAllCartProductsHandler(){
		
		List<Cart> cart = userService.getAllCartProducts();
		
		return new ResponseEntity<List<Cart>>(cart, HttpStatus.OK);
	}
	
	
	@GetMapping("/user/orders")
	public ResponseEntity<List<Orders>> getAllOrderdProductsHandler(){
		
		List<Orders> orders = userService.getAllOrderdProduct();
		
		return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
	}
	
	
	@GetMapping("/user/products")
	public ResponseEntity<List<Product>> getAllProductsHandler(){
		
		List<Product> products = userService.getAllProducts();
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	
	@GetMapping("/user/products/{keyword}")
	public ResponseEntity<List<Product>> searchProductHandler(@PathVariable String keyword){
		
		List<Product> products = userService.searchProducts(keyword);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/user/products/{keyword}/{rating}")
	public ResponseEntity<List<Product>> searchProductAndFilterByRatingHandler(@PathVariable String keyword, @PathVariable Integer rating){
		List<Product> products = userService.searchProductAndFilterByRating(keyword, rating);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/user/products/{keyword}/{min}/{max}")
	public ResponseEntity<List<Product>> searchProductAndFilterByPriceHandler(@PathVariable String keyword, @PathVariable Integer min, @PathVariable Integer max){
		
		List<Product> products = userService.searchProductAndFilterByPrice(keyword, min, max);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	
	@GetMapping("/user/products/{keyword}/{field}")
	public ResponseEntity<List<Product>> searchProductAndSortByField(@PathVariable String keyword, @PathVariable String field, @RequestParam(value = "order") String order){
		
		List<Product> products = userService.searchProductAndSort(keyword, field, order);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	@GetMapping("/user/products/com/{productid}")
	public ResponseEntity<Product> addRatingAndCommentHandler(@RequestBody Comment comment, @PathVariable Integer productid) throws ProductException{
		
		Product product = userService.addRatingAndComment(productid, comment);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	
	
	
}
