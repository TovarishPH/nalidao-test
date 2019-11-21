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

	public Optional<Product> findById(Long id) {
		return this.repository.findById(id);
	}
	
	public Product getOne(Long id) {
		return this.repository.getOne(id);
	}
	
	public void save(Product produto) {
		this.repository.save(produto);
	}

	public void deleteById(Long id) {
		this.repository.deleteById(id);
	}

}
