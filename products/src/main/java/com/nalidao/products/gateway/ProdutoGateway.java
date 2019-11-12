package com.nalidao.products.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nalidao.products.domain.Produto;
import com.nalidao.products.repository.ProdutoRepository;

@Service
public class ProdutoGateway {

	private ProdutoRepository repository;
	
	public ProdutoGateway(ProdutoRepository repository) {
		this.repository = repository;
	}
	
	public List<Produto> findAll() {
		return this.repository.findAll();
	}

	public Optional<Produto> findById(Long id) {
		return this.repository.findById(id);
	}
	
	public Produto getOne(Long id) {
		return this.repository.getOne(id);
	}
	
	public void save(Produto produto) {
		this.repository.save(produto);
	}

	public void deleteById(Long id) {
		this.repository.deleteById(id);
	}

}
