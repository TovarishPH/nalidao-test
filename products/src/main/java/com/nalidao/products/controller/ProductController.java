package com.nalidao.products.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nalidao.products.controller.converter.ProductDtoToEntityConverter;
import com.nalidao.products.controller.dto.ProductDto;
import com.nalidao.products.domain.Product;
import com.nalidao.products.service.ProductService;

@RestController
@RequestMapping("/product-api")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductDtoToEntityConverter converterToEntity;
	
	@GetMapping
	public List<Product> findAllProducts() {
		return this.service.findAll();
	}
	
	@GetMapping("/{id}")
	public Product findProductById(@PathVariable Long id) {
		return this.service.findById(id).get();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto productDto) {
		Product product = this.converterToEntity.convert(productDto);
		this.service.save(product);
		return ResponseEntity.ok(productDto);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeProduct(@PathVariable Long id) {
		this.service.remove(id);
		return ResponseEntity.ok("Produto removido da base de dados.");
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {
		Product product = this.converterToEntity.convert(productDto);
		this.service.update(id, product);
		return ResponseEntity.ok(productDto);
	}
}
