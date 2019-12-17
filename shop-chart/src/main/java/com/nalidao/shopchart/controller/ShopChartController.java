package com.nalidao.shopchart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nalidao.shopchart.domain.Product;
import com.nalidao.shopchart.domain.ShopChart;
import com.nalidao.shopchart.service.ShopChartService;

@RestController
@RequestMapping("/shop-chart")
public class ShopChartController {
	
	private ShopChartService service;
	
	public ShopChartController(ShopChartService service) {
		super();
		this.service = service;
	}
	
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
}
