package com.nalidao.products.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nalidao.products.model.Produto;
import com.nalidao.products.model.ProdutoDto;
import com.nalidao.products.service.ProdutoService;

@RestController
@RequestMapping("/produto-api")
public class ProdutoController {

	private ProdutoService service;
	
	public ProdutoController(ProdutoService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<Produto> buscaProdutos() {
		return this.service.findAll();
	}
	
	@GetMapping("/{id}")
	public Produto buscaPorId(@PathVariable Long id) {
		return this.service.findById(id).get();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastraProduto(@RequestBody ProdutoDto produtoDto) {
		this.service.create(produtoDto);
		return ResponseEntity.ok(produtoDto);
	}
	
	@DeleteMapping
	@Transactional
	public ResponseEntity<?> removerProduto(@PathParam(value = "id") Long id) {
		Optional<Produto> produto = this.service.findById(id);
		if (produto.isPresent()) {
			this.service.remove(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().build();
	}
}
