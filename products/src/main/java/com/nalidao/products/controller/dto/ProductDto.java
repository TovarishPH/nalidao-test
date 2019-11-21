package com.nalidao.products.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	
	private Long id;
	
	@Length(min = 4, message = "'Name' field have a minimun of 4 characters.")
	@NotEmpty(message = "'Name' field must not be empty.")
	private String name;
	
	@Positive(message = "'Price' field must be above zero (0)")
	private double price;
	
	@PositiveOrZero(message = "'Amount' field must be a positive number.")
	private int amount;

	public ProductDto(
			@Length(min = 4, message = "'Name' field have a minimun of 4 characters.") @NotEmpty(message = "'Name' field must not be empty.") String name,
			@Positive(message = "'Price' field must be above zero (0)") double price,
			@PositiveOrZero(message = "'Amount' field must be a positive number.") int amount) {
		super();
		this.name = name;
		this.price = price;
		this.amount = amount;
	}
	
}
