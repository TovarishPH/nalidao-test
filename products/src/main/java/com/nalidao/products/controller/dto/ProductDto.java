package com.nalidao.products.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
	
	@Length(min = 4, message = "'Name' field have a minimun of 4 characters.")
	@NotEmpty(message = "'Name' field must not be empty.")
	private String name;
	
	@Positive(message = "'Price' field must be above zero (0)")
	private double price;
	
	@PositiveOrZero(message = "'Amount' field must be a positive number.")
	private int amount;
}
