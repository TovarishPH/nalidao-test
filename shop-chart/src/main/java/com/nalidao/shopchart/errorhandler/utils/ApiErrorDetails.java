package com.nalidao.shopchart.errorhandler.utils;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorDetails {

	private String title;
	private String status;
	private String message;
	private String packagePath;
	private LocalDateTime timestamp;
	
}
