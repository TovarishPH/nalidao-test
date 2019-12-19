package com.nalidao.shopchart.controller.dto;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopChartDto {

	private BigInteger id;
	
	@NotNull
	private String name;
}
