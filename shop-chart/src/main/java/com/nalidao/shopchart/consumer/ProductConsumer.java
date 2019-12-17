package com.nalidao.shopchart.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nalidao.shopchart.domain.Product;

@Component
public class ProductConsumer {
	
	@Value("${product.api}")
	private String path;

	public Product getProduct(long id) {
		RestTemplate rt = new RestTemplate();
		Product product = rt.getForObject(path + "/" + String.valueOf(id), Product.class);
		return product;
		
	}
}
