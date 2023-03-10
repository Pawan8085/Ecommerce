package com.ecommerce.exceptions;

public class CartException extends RuntimeException{
	
	public CartException() {}
	
	public CartException(String msg) {
		super(msg);
	}

}
