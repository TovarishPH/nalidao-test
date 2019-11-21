package com.nalidao.products.errorhandling.utils;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiErrorDetails {

	private String title;
	private String status;
	private String message;
	private String packagePath;
	private LocalDateTime timestamp;

	public ApiErrorDetails(String title, String status, String message, String packagePath, LocalDateTime timestamp) {
		super();
		this.title = title;
		this.status = status;
		this.message = message;
		this.packagePath = packagePath;
		this.timestamp = timestamp;
	}

}