package com.nalidao.shopchart.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.shopchart.controller.dto.ProductDto;
import com.nalidao.shopchart.domain.Product;

@Component
public class ProductDtoToEntityConverter implements Converter<ProductDto, Product> {

	@Override
	public Product convert(ProductDto source) {
		return new Product(source.getId(), source.getName(), source.getPrice(), source.getAmount());
	}

}
