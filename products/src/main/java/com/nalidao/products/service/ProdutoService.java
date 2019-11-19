package com.nalidao.products.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.nalidao.products.domain.Produto;
import com.nalidao.products.errorhandling.exception.ProdutoNotFoundException;
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
		Optional<Produto> produto = this.gateway.findById(id);
		if (produto.isPresent()) {
			return produto;
		}
		throw new ProdutoNotFoundException("Produto id: " + id + " não encontrado.");
	}
	
	public void create(Produto produto) {
		this.gateway.save(produto);
	}
	
	public void remove(Long id) {
		Optional<Produto> produto = this.gateway.findById(id);
		if (produto.isPresent()) {
			this.gateway.deleteById(id);
		} else { 
			throw new ProdutoNotFoundException("Deleção não efetuada. Produto id " + id + " não encontrado na base de dados.");
		}
	}
	
	public void atualizar(Long id, Produto produto) {
		try {
			Produto encontrado = this.gateway.getOne(id);
			
			if (encontrado.getId() != null) {
				encontrado.setNome(produto.getNome());
				encontrado.setPreco(produto.getPreco());
				encontrado.setQuantidade(produto.getQuantidade());
				this.gateway.save(encontrado);
			}			
		} catch (EntityNotFoundException e) {
			throw new ProdutoNotFoundException("Atualização não efetuada. Produto id: " + id + " não encontrado.");
		}
		
	}
	
}
