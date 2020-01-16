package com.nalidao.shopchart.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.shopchart.controller.dto.FormInDto;
import com.nalidao.shopchart.controller.dto.ProductDto;
import com.nalidao.shopchart.controller.dto.ShopChartDto;

@Component
public class FormInDtoToDtoConverter implements Converter<FormInDto, ShopChartDto> {

	@Override
	public ShopChartDto convert(FormInDto source) {
		List<ProductDto> pDtoList = new ArrayList<ProductDto>();
		pDtoList.add(source.getProductDto());
		return new ShopChartDto(source.getId(), pDtoList);
	}

}
