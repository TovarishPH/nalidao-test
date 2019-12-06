package com.nalidao.shopchart.domain;

import java.util.List;

import lombok.Data;

@Data
public class ShopChart {

	private Long id;
	
	private Long customerId;
	
	private Customer customer;
	
	private List<Product> products;
}
