package com.nalidao.products.errorhandling.utils;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FormErrorDetails extends ApiErrorDetails {

	private String field;

	public FormErrorDetails(String title, String status, String message, String packagePath, LocalDateTime timestamp,
			String field) {
		super(title, status, message, packagePath, timestamp);
		this.field = field;
	}

}
