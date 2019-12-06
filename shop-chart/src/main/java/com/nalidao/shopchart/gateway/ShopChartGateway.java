package com.nalidao.shopchart.gateway;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nalidao.shopchart.domain.ShopChart;
import com.nalidao.shopchart.repository.ShopChartRepository;

@Component
public class ShopChartGateway {

	private ShopChartRepository repository;
	
	public Optional<ShopChart> findById(final Long id) {
		return this.repository.findById(id);
	}

	
}
