package com.nalidao.shopchart.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.shopchart.controller.dto.ProductDto;
import com.nalidao.shopchart.domain.Product;

@Component
public class ProductToDtoConverter implements Converter<Product, ProductDto> {

	@Override
	public ProductDto convert(Product source) {
		return new ProductDto(source.getId(), source.getName(), source.getPrice(), source.getAmount());
	}

}
