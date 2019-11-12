package com.nalidao.products.controller.error.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;
	private String message;
	private LocalDateTime time;
	
	public ApiError(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public ApiError(HttpStatus status, String message, LocalDateTime time) {
		super();
		this.status = status;
		this.message = message;
		this.time = time;
	}
	
}
