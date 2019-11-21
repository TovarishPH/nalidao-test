package com.nalidao.products.service;

import com.nalidao.products.domain.Produto;
import com.nalidao.products.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

	@InjectMocks
	private ProdutoService service;

	@Mock
	private ProdutoGateway gateway;

	@Test
	public void testFindAllProdutos() {
		//Dado uma lista de produtos existentes (given)
		List<Produto> listDeProdutosMock = getListDeProdutosMock();
		when(this.gateway.findAll()).thenReturn(listDeProdutosMock);

		//Quando estes produtos forem consultados (when)
		List<Produto> produtosEncontrados = this.service.findAll();

		//Entao os produtos deverão ser retornados (then)
		assertThat(produtosEncontrados).isNotNull().isNotEmpty();
		assertEquals(produtosEncontrados.size(), listDeProdutosMock.size());
	}

	@Test
	public void testFindById() {
		Produto produto = this.getProduto();

		when(this.gateway.findById(1l)).thenReturn(Optional.of(produto));

		assertThat(this.service.findById(1l)).contains(produto);
	}

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
