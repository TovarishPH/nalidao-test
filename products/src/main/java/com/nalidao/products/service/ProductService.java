package com.nalidao.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nalidao.products.domain.Product;
import com.nalidao.products.errorhandling.exception.ProductNotFoundException;
import com.nalidao.products.gateway.ProductGateway;

@Service
public class ProductService {

	private ProductGateway gateway;

	public ProductService(ProductGateway gateway) {
		this.gateway = gateway;
	}

	public List<Product> findAll() {
		return this.gateway.findAll();
	}

	public Optional<Product> findById(final Long id) {
		Optional<Product> produto = this.gateway.findById(id);
		if (produto.isPresent()) {
			return produto;
		}
		throw new ProductNotFoundException("Product id: " + id + " not found.");
	}

	public void save(final Product product) {
		this.gateway.save(product);
	}

	public void remove(final Long id) {
		Optional<Product> product = this.gateway.findById(id);
		if (product.isPresent()) {
			this.gateway.deleteById(id);
		} else {
			throw new ProductNotFoundException(
					"Delete not accomplished. Product id " + id + " not found in our data base.");
		}
	}

	public Product update(final Long id, final Product product) {
		Optional<Product> dbProduct = this.gateway.findById(id);

		if (dbProduct.isPresent()) {
			dbProduct.get().setName(product.getName());
			dbProduct.get().setPrice(product.getPrice());
			dbProduct.get().setAmount(product.getAmount());
			this.gateway.save(dbProduct.get());
			return dbProduct.get();
		} else {
			throw new ProductNotFoundException("Update not accomplished. Product id: " + id + " not found.");
		}

	}

}
