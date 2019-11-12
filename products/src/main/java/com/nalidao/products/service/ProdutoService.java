package com.nalidao.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nalidao.products.domain.Produto;
import com.nalidao.products.gateway.ProdutoGateway;

@Service
public class ProdutoService {
	
	private ProdutoGateway gateway;
	
	public ProdutoService(ProdutoGateway gateway) {
		this.gateway = gateway;
	}
	
	public List<Produto> findAll() {
		return this.gateway.findAll();
	}
	
	public Optional<Produto> findById(Long id) {
		return this.gateway.findById(id);
	}
	
	public void create(Produto produto) {
		this.gateway.save(produto);
	}
	
	public void remove(Long id) {
		Optional<Produto> produto = this.gateway.findById(id);
		if (produto.isPresent()) {
			this.gateway.deleteById(id);
		}
	}
	
	public void atualizar(Long id, Produto produto) {
		Produto encontrado = this.gateway.getOne(id);
		
		if (encontrado != null) {
			encontrado.setNome(produto.getNome());
			encontrado.setPreco(produto.getPreco());
			encontrado.setQuantidade(produto.getQuantidade());
			this.gateway.save(encontrado);
		}
	}
	
}
