package com.nalidao.shopchart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	public ShopChart getShopChartById(final Long id) {
		return this.service.findById(id);
	}
}
