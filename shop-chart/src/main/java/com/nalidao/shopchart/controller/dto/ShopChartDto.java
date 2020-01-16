package com.nalidao.shopchart.controller.dto;

import java.math.BigInteger;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopChartDto {

	private BigInteger id;
	
	private List<ProductDto> productDtoList;
}
