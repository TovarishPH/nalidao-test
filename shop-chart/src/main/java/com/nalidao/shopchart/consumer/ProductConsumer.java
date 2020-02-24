package com.nalidao.shopchart.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nalidao.shopchart.domain.Product;
import com.nalidao.shopchart.errorhandler.exception.ProductNotFoundexception;

@Component
public class ProductConsumer {
	
	@Value("${product.api}")
	private String path;

	public Product getProduct(long id) {
		RestTemplate rt = new RestTemplate();
		try {
			Product product = rt.getForObject(path + "/" + String.valueOf(id), Product.class);
			return product;
		} catch (Exception e) {
			throw new ProductNotFoundexception("Dammit! Product with id " + id + " not found.");
		}
		
	}
}
