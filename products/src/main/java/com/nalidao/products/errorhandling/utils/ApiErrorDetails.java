package com.nalidao.products.errorhandling.utils;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiErrorDetails {

	private String titulo;
	private String status;
	private String mensagem;
	private String pacote;
	private LocalDateTime timestamp;
	private Throwable throwable;

	public ApiErrorDetails(String titulo, String status, String mensagem, String pacote, LocalDateTime timestamp,
			Throwable throwable) {
		super();
		this.titulo = titulo;
		this.status = status;
		this.mensagem = mensagem;
		this.pacote = pacote;
		this.timestamp = timestamp;
		this.throwable = throwable;
	}

}