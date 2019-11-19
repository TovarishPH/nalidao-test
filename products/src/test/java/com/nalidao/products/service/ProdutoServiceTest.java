package com.nalidao.products.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nalidao.products.domain.Produto;
import com.nalidao.products.errorhandling.exception.ProdutoNotFoundException;
import com.nalidao.products.gateway.ProdutoGateway;
import com.nalidao.products.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {
	
	@InjectMocks
	private ProdutoService service;
	
	@InjectMocks
	private ProdutoGateway gateway;
	
	@Mock
	private ProdutoRepository repository;
	
	private List<Produto> produtos;
	
	@BeforeEach
	public void init() {
		this.service = new ProdutoService(gateway);
		this.gateway = new ProdutoGateway(repository);
		
		this.produtos = this.getListDeProdutosMock();
	}

	@Test
	public void testFindAllProdutos() {
		when(this.repository.findAll()).thenReturn(this.produtos);
		when(this.gateway.findAll()).thenReturn(this.produtos);
		
		assertThat(this.service.findAll()).isNotNull().isNotEmpty();
		assertEquals(2, this.produtos.size());
	}
	
	@Test
	public void testFindById() {
		Produto produto = this.getProduto();
		
		when(this.repository.findById(1l)).thenReturn(Optional.of(produto));
		when(this.gateway.findById(1l)).thenReturn(Optional.of(produto));
		
		assertThat(this.service.findById(1l)).contains(produto);
	}
	
//	@Test
//	public void testFindByIdException() {
//		Mockito.doThrow(IllegalArgumentException.class).when(this.repository.findById(1l));
//		Mockito.doThrow(IllegalArgumentException.class).when(this.gateway.findById(1l));
//		Mockito.doThrow(ProdutoNotFoundException.class).when(this.service.findById(1l));
//	}
	
	@Test
	public void testCreate() {
		//Comparando tamanho da lista depois de executar o método save
//		assertEquals(3, this.produtos.size());
	}
	
	private List<Produto> getListDeProdutosMock() {
		List<Produto> produtos = new ArrayList<Produto>();
		
		Produto p1 = new Produto("Produto 1", 1.5, 5);
		p1.setId(1l);
		produtos.add(p1);
		
		Produto p2 = new Produto("Produto 2", 3.5, 2);
		p2.setId(2l);
		produtos.add(p2);
		
		return produtos;
	}
	
	private Produto getProduto() {
		Produto p = new Produto("Produto", 100.5, 10);
		p.setId(1l);
		
		return p;
	}
}
