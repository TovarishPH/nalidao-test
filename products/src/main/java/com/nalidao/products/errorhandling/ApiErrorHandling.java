package com.nalidao.products.errorhandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nalidao.products.errorhandling.exception.ProdutoNotFoundException;
import com.nalidao.products.errorhandling.utils.ApiErrorDetails;

@ControllerAdvice
public class ApiErrorHandling {

	@ExceptionHandler(ProdutoNotFoundException.class)
	private ResponseEntity<ApiErrorDetails> handleProdutoNotFoundException(ProdutoNotFoundException e) {
		ApiErrorDetails details = new ApiErrorDetails(
				                      "Produto não encontrado",
				                      HttpStatus.NOT_FOUND.toString(),
				                      e.getLocalizedMessage(),
				                      e.getClass().getPackage().toString(),
				                      LocalDateTime.now(),
				                      e);
		
		return new ResponseEntity<ApiErrorDetails>(details, HttpStatus.NOT_FOUND);
	}
}
