package com.nalidao.shopchart.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.shopchart.controller.dto.ShopChartDto;
import com.nalidao.shopchart.domain.Product;
import com.nalidao.shopchart.domain.ShopChart;

@Component
public class ShopChartDtoConverterToEntity implements Converter<ShopChartDto, ShopChart> {

	@Autowired
	private ProductDtoToEntityConverter productDtoToEntityConverter;
	
	@Override
	public ShopChart convert(ShopChartDto dto) {
		List<Product> productList = new ArrayList<Product>();
		dto.getProductDtoList().forEach(p -> {
			Product product = this.productDtoToEntityConverter.convert(p);
			productList.add(product);
		});
		return new ShopChart(dto.getId(), productList); 
							
	}

}
