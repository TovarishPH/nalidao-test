package com.nalidao.shopchart.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalidao.shopchart.consumer.ProductConsumer;
import com.nalidao.shopchart.controller.dto.ShopChartDto;
import com.nalidao.shopchart.domain.Product;
import com.nalidao.shopchart.domain.ShopChart;
import com.nalidao.shopchart.gateway.ShopChartGateway;

@Service
public class ShopChartService {

	@Autowired
	private ShopChartGateway gateway;
	
	@Autowired
	private ProductConsumer consumer;

	//TESTE DE CONSUMER
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return this.consumer.getProduct(id);
	}

	public List<ShopChart> findAll() {
		return this.gateway.findAll();
	}
	
	public Optional<ShopChart> findById(final BigInteger id) {
		return this.gateway.findById(id);
	}

	public void createShopChart(ShopChart shopChart) {
		this.gateway.createShopChart(shopChart);
	}

	public void remove(BigInteger id) {
		this.gateway.remove(id);
		
	}

}
