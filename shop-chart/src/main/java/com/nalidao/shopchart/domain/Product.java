package com.nalidao.shopchart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private long id;
	
	private String name;
	
	private double price;
	
	private long amount;
}
