package com.nalidao.shopchart.errorhandler.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopChartNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public ShopChartNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	

}
