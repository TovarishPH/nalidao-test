package com.nalidao.shopchart.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalidao.shopchart.consumer.ProductConsumer;
import com.nalidao.shopchart.domain.Product;
import com.nalidao.shopchart.domain.ShopChart;
import com.nalidao.shopchart.errorhandler.exception.ProductNotFoundexception;
import com.nalidao.shopchart.errorhandler.exception.ShopChartNotFoundException;
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
		Optional<ShopChart> shopChart = this.gateway.findById(id);
		if(shopChart.isPresent()) {
			return shopChart;
		}
		throw new ShopChartNotFoundException("ShopChart id " + id + " not found.");
	}

	public void createShopChart(ShopChart shopChart) {
		long productId = shopChart.getProductList().get(0).getId();
		if(this.validateProduct(productId)) {
			this.gateway.createShopChart(shopChart);
		} else {
			throw new ProductNotFoundexception("Product id " + productId + " not found.");
		}
	}

	public void remove(BigInteger id) {
		Optional<ShopChart> shopChart = this.gateway.findById(id);
		if(shopChart.isPresent()) {
			this.gateway.remove(id);
		} else {
			throw new ShopChartNotFoundException("Delete action cannot be done. ShopChart id " + id + " not found.");
		}
		
	}
	
	private boolean validateProduct(Long productId) {
		Product product = this.consumer.getProduct(productId);
		if(product != null) {
			return true;
		}
		return false;
	}

}
