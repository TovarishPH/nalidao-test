package com.nalidao.shopchart.controller.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FormInDto {

	private BigInteger id;
	
	private ProductDto productDto;
}
