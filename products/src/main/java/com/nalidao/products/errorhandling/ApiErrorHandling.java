package com.nalidao.products.errorhandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nalidao.products.errorhandling.exception.ProductNotFoundException;
import com.nalidao.products.errorhandling.utils.ApiErrorDetails;
import com.nalidao.products.errorhandling.utils.FormErrorDetails;

@ControllerAdvice
public class ApiErrorHandling {
	
	@ExceptionHandler(ProductNotFoundException.class)
	private ResponseEntity<ApiErrorDetails> handleProdutoNotFoundException(ProductNotFoundException e) {
		ApiErrorDetails details = new ApiErrorDetails(
				                      "Product not found.",
				                      HttpStatus.NOT_FOUND.toString(),
				                      e.getLocalizedMessage(),
				                      e.getClass().getPackage().toString(),
				                      LocalDateTime.now());
		
		return new ResponseEntity<ApiErrorDetails>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<List<FormErrorDetails>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<FormErrorDetails> details = new ArrayList<FormErrorDetails>();
		
		List<FieldError> fields = e.getBindingResult().getFieldErrors();
		fields.forEach(field -> {
			FormErrorDetails detail = new FormErrorDetails(
											"Form error.",
											HttpStatus.BAD_REQUEST.toString(),
											field.getDefaultMessage(),
											e.getClass().getPackage().toString(),
											LocalDateTime.now(),
											field.getField());
			details.add(detail);
		});
		
		return new ResponseEntity<List<FormErrorDetails>>(details, HttpStatus.BAD_REQUEST);
	}
	
}
