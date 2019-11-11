package com.nalidao.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nalidao.products.model.Produto;
import com.nalidao.products.model.ProdutoDto;
import com.nalidao.products.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private ProdutoRepository repository;
	
	public ProdutoService(ProdutoRepository repository) {
		this.repository = repository;
	}

	public List<Produto> findAll() {
		return this.repository.findAll();
	}
	
	public Optional<Produto> findById(Long id) {
		return this.repository.findById(id);
	}
	
	public void create(ProdutoDto produtoDto) {
		Produto produto = converter(produtoDto);
		this.repository.save(produto);
	}
	
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	public void atualizar(Long id, ProdutoDto dto) {
		Optional<Produto> produto = this.repository.findById(id);
		
	}
	
	private Produto converter(ProdutoDto dto) {
		return new Produto(dto.getNome(), dto.getPreco(), dto.getQuantidade());
	}

}
