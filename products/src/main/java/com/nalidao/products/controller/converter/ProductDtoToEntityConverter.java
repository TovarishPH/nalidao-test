package com.nalidao.products.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.products.controller.dto.ProductDto;
import com.nalidao.products.domain.Product;

@Component
public class ProductDtoToEntityConverter implements Converter<ProductDto, Product> {

	@Override
	public Product convert(ProductDto dto) {
		return new Product(dto.getName(), dto.getPrice(), dto.getAmount());
	}

}
