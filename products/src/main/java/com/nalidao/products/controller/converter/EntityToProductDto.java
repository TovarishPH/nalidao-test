package com.nalidao.products.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.products.controller.dto.ProductDto;
import com.nalidao.products.domain.Product;

@Component
public class EntityToProductDto implements Converter<Product, ProductDto> {

	@Override
	public ProductDto convert(Product p) {
		return new ProductDto(p.getId(), p.getName(), p.getPrice(), p.getAmount());
	}

}
