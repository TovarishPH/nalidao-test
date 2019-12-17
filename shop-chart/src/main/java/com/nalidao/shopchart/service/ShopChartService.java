package com.nalidao.shopchart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nalidao.shopchart.consumer.ProductConsumer;
import com.nalidao.shopchart.domain.Product;
import com.nalidao.shopchart.domain.ShopChart;
import com.nalidao.shopchart.gateway.ShopChartGateway;

@Service
public class ShopChartService {

	private ShopChartGateway gateway;
	
	private ProductConsumer consumer;

	public ShopChartService(ShopChartGateway gateway, ProductConsumer consumer) {
		super();
		this.gateway = gateway;
		this.consumer = consumer;
	}
	
	//TESTE DE CONSUMER
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return this.consumer.getProduct(id);
	}

	public List<ShopChart> findAll() {
		return this.gateway.findAll();
	}
	
	public Optional<ShopChart> findById(final Long id) {
		return this.gateway.findById(id);
	}

}
