package com.nalidao.products.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDto {
	
	@Length(min = 4, message = "Obrigatório mínimo de 4 caracteres para o campo 'Nome'.")
	@NotEmpty(message = "Campo 'Nome' deve ser preenchido.")
	private String nome;
	
	@Positive(message = "Campo 'Preço' deve ser maior que 0 (Zero).")
	private double preco;
	
	@PositiveOrZero(message = "Campo 'Quantidade' precisa ser um número positivo.")
	private int quantidade;
}
