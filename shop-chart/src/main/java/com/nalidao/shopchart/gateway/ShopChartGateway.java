package com.nalidao.shopchart.gateway;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nalidao.shopchart.domain.ShopChart;
import com.nalidao.shopchart.repository.ShopChartRepository;

@Component
public class ShopChartGateway {

	private ShopChartRepository repository;
	
	public ShopChartGateway(ShopChartRepository repository) {
		super();
		this.repository = repository;
	}
	
	public List<ShopChart> findAll() {
		return this.repository.findAll();
	}

	public Optional<ShopChart> findById(final BigInteger id) {
		return this.repository.findById(id);
	}

	public void createShopChart(ShopChart shopChart) {
		this.repository.save(shopChart);
	}

	public void remove(BigInteger id) {
		this.repository.deleteById(id);
	}
	
}
