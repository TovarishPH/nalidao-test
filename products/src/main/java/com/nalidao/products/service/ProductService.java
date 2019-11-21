package com.nalidao.products.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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
	
	public Optional<Product> findById(Long id) {
		Optional<Product> produto = this.gateway.findById(id);
		if (produto.isPresent()) {
			return produto;
		}
		throw new ProductNotFoundException("Product id: " + id + " not found.");
	}
	
	public void save(Product product) {
		this.gateway.save(product);
	}
	
	public void remove(Long id) {
		Optional<Product> produto = this.gateway.findById(id);
		if (produto.isPresent()) {
			this.gateway.deleteById(id);
		} else { 
			throw new ProductNotFoundException("Delete not accomplished. Product id " + id + " not found in our data base.");
		}
	}
	
	public void update(Long id, Product product) {
		try {
			Product dbProduct = this.gateway.getOne(id);
			
			if (dbProduct.getId() != null) {
				dbProduct.setName(product.getName());
				dbProduct.setPrice(product.getPrice());
				dbProduct.setAmount(product.getAmount());
				this.gateway.save(dbProduct);
			}			
		} catch (EntityNotFoundException e) {
			throw new ProductNotFoundException("Update not accomplished. Product id: " + id + " not found.");
		}
		
	}
	
}
