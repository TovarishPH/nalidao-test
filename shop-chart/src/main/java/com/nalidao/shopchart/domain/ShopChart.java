package com.nalidao.shopchart.domain;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Document
@AllArgsConstructor
public class ShopChart {

	@Id
	private BigInteger id;
	
	private List<Product> productList;
}
