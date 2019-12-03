package com.nalidao.products.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nalidao.products.domain.Product;
import com.nalidao.products.repository.ProductRepository;

@Component
public class ProductGateway {

	private ProductRepository repository;
	
	public ProductGateway(ProductRepository repository) {
		this.repository = repository;
	}
	
	public List<Product> findAll() {
		return this.repository.findAll();
	}

	public Optional<Product> findById(final Long id) {
		return this.repository.findById(id);
	}
	
	public void save(final Product product) {
		this.repository.save(product);
	}

	public void deleteById(final Long id) {
		this.repository.deleteById(id);
	}

}
