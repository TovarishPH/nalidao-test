package com.nalidao.shopchart.domain;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class ShopChart {

	@Id
	private Long id;
	
	private Long customerId;
	
	private List<Product> products;
}
