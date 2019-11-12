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

import com.nalidao.products.controller.converter.ProdutoDtoToEntityConverter;
import com.nalidao.products.controller.dto.ProdutoDto;
import com.nalidao.products.domain.Produto;
import com.nalidao.products.service.ProdutoService;

@RestController
@RequestMapping("/produto-api")
public class ProdutoController {

	@Autowired
	private ProdutoService service;
	
	@Autowired
	private ProdutoDtoToEntityConverter converter;
	
	@GetMapping
	public List<Produto> buscaProdutos() {
		return this.service.findAll();
	}
	
	@GetMapping("/{id}")
	public Produto buscaPorId(@PathVariable Long id) {
		//tratar NoSuchElementException/NotFoundException
		return this.service.findById(id).get();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> cadastraProduto(@RequestBody @Valid ProdutoDto produtoDto) {
		Produto produto = this.converter.convert(produtoDto);
		this.service.create(produto);
		return ResponseEntity.ok(produtoDto);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removerProduto(@PathVariable Long id) {
		this.service.remove(id);
		return ResponseEntity.ok("Produto removido da base de dados.");
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
		Produto produto = this.converter.convert(produtoDto);
		this.service.atualizar(id, produto);
		return ResponseEntity.ok(produtoDto);
	}
}
