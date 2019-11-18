package com.nalidao.products.errorhandling.utils;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FormErrorDetails extends ApiErrorDetails {

	private String campo;

	public FormErrorDetails(String titulo, String status, String mensagem, String pacote, LocalDateTime timestamp,
			String campo) {
		super(titulo, status, mensagem, pacote, timestamp);
		this.campo = campo;
	}

}
