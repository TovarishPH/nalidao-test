package com.nalidao.shopchart.controller;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nalidao.shopchart.controller.dto.FormInDto;
import com.nalidao.shopchart.controller.dto.ShopChartDto;
import com.nalidao.shopchart.converter.ShopChartDtoConverterToEntity;
import com.nalidao.shopchart.converter.FormInDtoToDtoConverter;
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
	
	@Autowired
	private FormInDtoToDtoConverter formConverter;
	
	
	//TESTE DE CONSUMER
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable final Long id) {
		Product prod = this.service.getProductById(id);
		return ResponseEntity.ok(prod);
	}
	
	@GetMapping
	public List<ShopChart> getAllShopChartList() {
		return this.service.findAll();
	}

	@GetMapping("/{id}")
	public ShopChart findShopChartById(@PathVariable final BigInteger id) {
		return this.service.findById(id).get();
	}

	@PostMapping
	public ResponseEntity<ShopChartDto> createShopChart(@RequestBody @Valid final FormInDto formDto, UriComponentsBuilder builder) {
		formDto.setId(null);
		ShopChartDto dto = this.formConverter.convert(formDto);
		ShopChart shopChart = this.converterToEntity.convert(dto);
		this.service.createShopChart(shopChart);
		ShopChartDto dtoResponse = this.converterToDto.convert(shopChart);
		
		URI uri = builder.path("/shop-chart/{id}").buildAndExpand(dtoResponse.getId()).toUri();
		return ResponseEntity.created(uri).body(dtoResponse);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeShopChart(@PathVariable final BigInteger id) {
		this.service.remove(id);
		return ResponseEntity.ok("ShopChart id " + id + " has been removed.");
	}
}
