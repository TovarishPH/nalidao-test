package com.nalidao.shopchart.domain;

import lombok.Data;

@Data
public class Product {

	private long id;
	
	private String name;
	
	private double price;
	
	private long amount;
}
