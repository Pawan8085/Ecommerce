package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.exceptions.CartException;
import com.ecommerce.exceptions.CommentException;
import com.ecommerce.exceptions.ProductException;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Comment;
import com.ecommerce.model.Orders;
import com.ecommerce.model.Product;
import com.ecommerce.model.ProductDetails;
import com.ecommerce.model.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.CommentRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductDetailsRespository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductDetailsRespository productDetailsRespository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}



	@Override
	public String buyProduct(Integer productId, Integer quantity) throws ProductException {
		
		Optional<Product> optProduct=  productRepository.findById(productId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Optional<User> optUser = userRepository.findByEmail(userName);
		
		User user=optUser.get();
		
		if(optProduct.isPresent()) {
			
			Product product= optProduct.get();
			
			if(product.getIsOutOfStock()) {
				throw new ProductException("Out of stock!");
			}else {
				if(product.getProductStock()-quantity<0) {
					throw new ProductException("There is only "+product.getProductStock()+" product available");
				}
				else {
					
					//Reducing Product Quantity
					product.setProductStock(product.getProductStock()-quantity);
					
					//checking product is Out of Stock or not
					if(product.getProductStock()==0) {
						product.setIsOutOfStock(true);
					}
					
					productRepository.save(product);
					
					//Adding product into their order
					Orders ord=new Orders();			
					ord.setProductId(productId);
					ord.setIsOutofStok(product.getIsOutOfStock());
					ord.setProductImg(product.getImgaeLink());
					ord.setProductName(product.getProductName());
					ord.setProductPrice(product.getProductPrice());
					ord.setProductQuantity(quantity);
					
					user.getOrders().add(ord);
					ord.setUser(user);
					
					orderRepository.save(ord);
					
					
					//Updating product details
					Optional<ProductDetails> optProductDetails = productDetailsRespository.findByProductId(productId);
					
					if(optProductDetails.isPresent()) {
						
						ProductDetails productDetails = optProductDetails.get();
						
						productDetails.setSold(productDetails.getSold()+quantity);
						productDetails.setTotalRevenue(productDetails.getTotalRevenue() + quantity*product.getProductPrice());
						
						productDetailsRespository.save(productDetails);
					}
					
					
					
					//updating cart
					Optional<Cart> optCart = cartRepository.findByProductId(productId);
					
					if(optCart.isPresent()) {
						
						Cart cart = optCart.get();
						
						cart.setIsOutofStok(product.getIsOutOfStock());
						cartRepository.save(cart);
					}
	
					return "Your product will be delivered within some days"; 
				}
			}
				
		}else {
			throw new ProductException("Invalid product id "+productId);
		}
	}



	@Override
	public String addToCart(Integer productId, Integer quantity) throws ProductException, CartException {
		
		Optional<Cart> optCart = cartRepository.findByProductId(productId);
		
		if(optCart.isPresent()) {
			throw new CartException("Product already available in cart! ");
		}
		
		Optional<Product> optProduct=  productRepository.findById(productId);
		
          if(optProduct.isPresent()) {
			
			Product product= optProduct.get();
			
			if(product.getIsOutOfStock()) {
				throw new ProductException("Out of stock!");
			}else {
				if(product.getProductStock()-quantity<0) {
					throw new ProductException("There is only "+product.getProductStock()+" product available");
				}
				else {	
		
					Cart cart =new Cart();
					cart.setIsOutofStok(product.getIsOutOfStock());
					cart.setProductImg(product.getImgaeLink());
					cart.setProductName(product.getProductName());
					cart.setProductPrice(product.getProductPrice());
					cart.setProductQuantity(quantity);
					
					cartRepository.save(cart);
					return "Product added to cart"; 
				}
			}
				
		}else {
			throw new ProductException("Invalid product id "+productId);
		}
		
		
	}



	@Override
	public String byfromCart(Integer cartId) throws CartException, ProductException {
		
		
		Optional<Cart> optCart = cartRepository.findById(cartId);
		
		if(optCart.isPresent()) {
			Cart cart = optCart.get();
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userName = authentication.getName();
			Optional<User> optUser = userRepository.findByEmail(userName);
			
			User user=optUser.get();
			Optional<Product> optProduct=  productRepository.findById(cart.getProductId());
			if(optProduct.isPresent()) {
				
				Product product= optProduct.get();
				
				if(product.getIsOutOfStock()) {
					throw new ProductException("Out of stock!");
				}else {
					if(product.getProductStock()-cart.getProductQuantity()<0) {
						throw new ProductException("There is only "+product.getProductStock()+" product available");
					}
					else {
						
						//Reducing Product Quantity
						product.setProductStock(product.getProductStock()-cart.getProductQuantity());
						
						//checking product is Out of Stock or not
						if(product.getProductStock()==0) {
							product.setIsOutOfStock(true);
						}
						
						productRepository.save(product);
						
						//Adding product into order
						Orders ord=new Orders();			
						ord.setProductId(cart.getProductId());
						ord.setIsOutofStok(product.getIsOutOfStock());
						ord.setProductImg(product.getImgaeLink());
						ord.setProductName(product.getProductName());
						ord.setProductPrice(product.getProductPrice());
						ord.setProductQuantity(cart.getProductQuantity());
						
						user.getOrders().add(ord);
						ord.setUser(user);
						
						orderRepository.save(ord);
						
						
						//Updating product details
						Optional<ProductDetails> optProductDetails = productDetailsRespository.findByProductId(cart.getProductId());
						
						if(optProductDetails.isPresent()) {
							
							ProductDetails productDetails = optProductDetails.get();
							
							productDetails.setSold(productDetails.getSold()+cart.getProductQuantity());
							productDetails.setTotalRevenue(productDetails.getTotalRevenue() + cart.getProductPrice()*cart.getProductQuantity());
							
							productDetailsRespository.save(productDetails);
						}
						
						
						cart.setIsOutofStok(product.getIsOutOfStock());
						
						cartRepository.save(cart);
		
						return "Your product will be delivered within some days"; 
					}
				}
					
			}else {
				throw new ProductException("Invalid product id "+cart.getProductId());
			}
		}
		throw new CartException("Invalid cart id "+cartId);
	}



	@Override
	public List<Cart> getAllCartProducts() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Optional<User> optUser = userRepository.findByEmail(userName);
		
		
		User user = optUser.get();
		
		List<Cart> cart = user.getCarts();
		
		return cart;
	}



	@Override
	public List<Orders> getAllOrderdProduct() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Optional<User> optUser = userRepository.findByEmail(userName);
		
		
		User user = optUser.get();
		
		List<Orders> orders = user.getOrders();
		
		return orders;
	}



	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}



	@Override
	public List<Product> searchProducts(String keyword) {
		
		return productRepository.searchProduct(keyword);
		
		
	}


	@Override
	public List<Product> searchProductAndFilterByRating(String keyworad, Integer rating) {
		
		return productRepository.searchProductByKeywordAndRating(keyworad, rating);
	}


	@Override
	public List<Product> searchProductAndFilterByPrice(String keyword, Integer minPrice, Integer maxPrice) {
		
		return productRepository.searchProductByKeywordAndPriceRange(keyword, minPrice, maxPrice);
	}


	@Override
	public List<Product> searchProductAndSort(String keyword, String field, String order) {
		
		return productRepository.searchProductAndSortByField(keyword, field, order);
	}


	@Override
	public Product addRatingAndComment(Integer productid, Comment comment) throws ProductException {
		Optional<Product> optProduct=  productRepository.findById(productid);
		
		if(optProduct.isPresent()) {
			
			// *Handling Wrong Rating
			if(comment.getRating()<1 || comment.getRating()>5)throw new CommentException("Invalid Rating!");
			
			Product product = optProduct.get();
			
			product.setRatingCount(product.getRatingCount()+1);
			
			product.setRatingSum(product.getRatingSum()+ comment.getRating());
			
			product.setProductRating(product.getRatingSum()/product.getRatingCount());
			

			//Getting the current user from Authentication object
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userName = authentication.getName();
			Optional<User> optUser = userRepository.findByEmail(userName);
			
			User user=optUser.get();
			
			comment.setUserName(user.getFirstName()+" "+user.getLastName());
			comment.setUserAddress(user.getAddress());
			comment.setUserId(user.getUserId());
			
			product.getComments().add(comment);
			comment.setProduct(product);
			
			commentRepository.save(comment);
			
			return product;
			
			
		}
		
		 throw new ProductException("Invalid product id "+productid);
	}



	




}
