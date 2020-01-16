package com.nalidao.shopchart.errorhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nalidao.shopchart.errorhandler.exception.ProductNotFoundexception;
import com.nalidao.shopchart.errorhandler.exception.ShopChartNotFoundException;
import com.nalidao.shopchart.errorhandler.utils.ApiErrorDetails;

@ControllerAdvice
public class ApiErrorHandling {

	@ExceptionHandler(ShopChartNotFoundException.class)
	private ResponseEntity<ApiErrorDetails> handleShopChartNotFound(ShopChartNotFoundException e) {
		ApiErrorDetails details = new ApiErrorDetails("Shopchart not found.",
													HttpStatus.NOT_FOUND.toString(),
													e.getLocalizedMessage(),
													e.getClass().getPackage().toString(),
													LocalDateTime.now());	
		return new ResponseEntity<ApiErrorDetails>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNotFoundexception.class)
	private ResponseEntity<ApiErrorDetails> handlePuctNotFound(ProductNotFoundexception e) {
		ApiErrorDetails details = new ApiErrorDetails("Product not found.",
													HttpStatus.NOT_FOUND.toString(),
													e.getLocalizedMessage(),
													e.getClass().getPackage().toString(),
													LocalDateTime.now());	
		return new ResponseEntity<ApiErrorDetails>(details, HttpStatus.NOT_FOUND);
	}
}
