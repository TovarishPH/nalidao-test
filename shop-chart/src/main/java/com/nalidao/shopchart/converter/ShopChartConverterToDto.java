package com.nalidao.shopchart.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.shopchart.controller.dto.ShopChartDto;
import com.nalidao.shopchart.domain.ShopChart;

@Component
public class ShopChartConverterToDto implements Converter<ShopChart, ShopChartDto> {

	@Override
	public ShopChartDto convert(ShopChart entity) {
		return new ShopChartDto(entity.getId(), entity.getName());
	}

}
