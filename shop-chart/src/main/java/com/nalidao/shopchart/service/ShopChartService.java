package com.nalidao.shopchart.service;

import org.springframework.stereotype.Service;

import com.nalidao.shopchart.domain.ShopChart;
import com.nalidao.shopchart.gateway.ShopChartGateway;

@Service
public class ShopChartService {

	private ShopChartGateway gateway;

	public ShopChartService(ShopChartGateway gateway) {
		super();
		this.gateway = gateway;
	}

	public ShopChart findById(final Long id) {
		return this.gateway.findById(id);
	}

}
