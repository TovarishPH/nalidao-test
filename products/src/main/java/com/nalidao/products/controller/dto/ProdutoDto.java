package com.nalidao.products.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDto {
	
	@NotNull
	@NotEmpty
	private String nome;
	
	@NotNull
	private double preco;
	
	@NotNull
	private int quantidade;
}
