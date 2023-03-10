package com.ecommerce.exceptions;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	//AdminException
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> myAdminExceptionHandler(AdminException ae, WebRequest wr){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestap(LocalDateTime.now());
		err.setMessage(ae.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	//UserException
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> myUserExceptionHandler(UserException ue, WebRequest wr){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestap(LocalDateTime.now());
		err.setMessage(ue.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	//global exception handleR
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception e, WebRequest wr){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestap(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	//NoHandlerFound Exception
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException nfe, WebRequest wr){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestap(LocalDateTime.now());
		err.setMessage(nfe.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	//Validation Exception Handler
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> validationFoundExceptionHandler(MethodArgumentNotValidException me, WebRequest wr){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestap(LocalDateTime.now());
		err.setMessage("Validation error");
		err.setDetails(me.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
}
