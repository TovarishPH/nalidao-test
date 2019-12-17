package com.nalidao.shopchart.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nalidao.shopchart.controller.dto.ShopChartDto;
import com.nalidao.shopchart.converter.ShopChartDtoConverterToEntity;
import com.nalidao.shopchart.converter.ShopChartConverterToDto;
import com.nalidao.shopchart.domain.Product;
import com.nalidao.shopchart.domain.ShopChart;
import com.nalidao.shopchart.service.ShopChartService;

@RestController
@RequestMapping("/shop-chart")
public class ShopChartController {
	
	@Autowired
	private ShopChartService service;
	
	@Autowired
	private ShopChartDtoConverterToEntity converterToEntity;
	
	@Autowired
	private ShopChartConverterToDto converterToDto;
	
	
	
	//TESTE DE CONSUMER
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable final Long id) {
		Product prod = this.service.getProductById(id);
		return ResponseEntity.ok(prod);
	}
	
	public List<ShopChart> findAllShopChart() {
		return this.service.findAll();
	}

	@GetMapping("/{id}")
	public ShopChart findShopChartById(final Long id) {
		return this.service.findById(id).get();
	}
	
	public ResponseEntity<ShopChartDto> createShopChart(@RequestBody ShopChartDto dto, UriComponentsBuilder builder) {
		ShopChart shopChart = this.converterToEntity.convert(dto);
		Long shopChartId = this.service.createShopChart(shopChart);
		
		ShopChartDto dtoResponse = this.converterToDto.convert(shopChart);
		
		URI uri = builder.path("/shop-chart/{id}").buildAndExpand(dtoResponse.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dtoResponse);
	}
}
