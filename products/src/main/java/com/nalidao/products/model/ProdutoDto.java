package com.nalidao.products.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class ProdutoDto {
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private double preco;
	
	@Getter @Setter
	private int quantidade;
}
