package com.nalidao.products.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.util.UriComponentsBuilder;

import com.nalidao.products.controller.converter.EntityToProductDto;
import com.nalidao.products.controller.converter.ProductDtoToEntityConverter;
import com.nalidao.products.controller.dto.ProductDto;
import com.nalidao.products.domain.Product;
import com.nalidao.products.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/product-api")
@Api(value = "Product Management API")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductDtoToEntityConverter converterToEntity;
	
	@Autowired
	private EntityToProductDto convertToDto;
	
	@GetMapping
	@ApiOperation(value = "List of Products")
	public List<ProductDto> findAllProducts() {
		List<Product> productList = this.service.findAll();
		List<ProductDto> productDtoList = productList.stream().map(p -> this.convertToDto.convert(p)).collect(Collectors.toList());
		return productDtoList;
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Find Product by ID")
	public Product findProductById(@PathVariable final Long id) {
		return this.service.findById(id).get();
	}
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Create Product in Database")
	public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid final ProductDto productDto, UriComponentsBuilder uriBuilder) {
		Product product = this.converterToEntity.convert(productDto);
		this.service.save(product);
		ProductDto productDtoResponse = this.convertToDto.convert(product);
		
		URI uri = uriBuilder.path("/product-api/{id}").buildAndExpand(productDto.getId()).toUri();
		return ResponseEntity.created(uri).body(productDtoResponse);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Removing Product from Database")
	public ResponseEntity<?> removeProduct(@PathVariable final Long id) {
		this.service.remove(id);
		return ResponseEntity.ok("Action accomplished. Product id " + id + " was deleted from database.");
	}
	
	@PutMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Updating Product Information")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable final Long id, @RequestBody @Valid final ProductDto productDto) {
		Product product = this.converterToEntity.convert(productDto);
		product = this.service.update(id, product);
		ProductDto productDtoResponse = this.convertToDto.convert(product);
		return ResponseEntity.ok(productDtoResponse);
	}
}
