package com.nalidao.shopchart.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.shopchart.controller.dto.ShopChartDto;
import com.nalidao.shopchart.domain.ShopChart;

@Component
public class ShopChartDtoConverterToEntity implements Converter<ShopChartDto, ShopChart> {

	@Override
	public ShopChart convert(ShopChartDto dto) {
		return new ShopChart(dto.getId(), dto.getName());
	}

}
