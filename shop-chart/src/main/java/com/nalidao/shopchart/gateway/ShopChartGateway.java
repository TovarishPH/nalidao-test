package com.nalidao.shopchart.gateway;

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

	public Optional<ShopChart> findById(final Long id) {
		return this.repository.findById(id);
	}

	public Long createShopChart(ShopChart shopChart) {
		// TODO Auto-generated method stub
		this.repository.save(shopChart);
		return null;
	}
	
}
