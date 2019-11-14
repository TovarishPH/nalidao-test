package com.nalidao.products.errorhandling.exception;

public class ProdutoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProdutoNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ProdutoNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
