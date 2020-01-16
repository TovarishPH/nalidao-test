package com.nalidao.shopchart.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.shopchart.controller.dto.ProductDto;
import com.nalidao.shopchart.controller.dto.ShopChartDto;
import com.nalidao.shopchart.domain.ShopChart;

@Component
public class ShopChartConverterToDto implements Converter<ShopChart, ShopChartDto> {

	@Autowired
	private ProductToDtoConverter toDtoConverter;
	
	@Override
	public ShopChartDto convert(ShopChart entity) {
		List<ProductDto> productDtoList = new ArrayList<ProductDto>();
		entity.getProductList().forEach(p -> {
			ProductDto pDto = this.toDtoConverter.convert(p);
			productDtoList.add(pDto);
		});
		return new ShopChartDto(entity.getId(), productDtoList);
	}

}
