package com.nalidao.products.service;

import com.nalidao.products.domain.Product;
import com.nalidao.products.gateway.ProductGateway;
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
	private ProductService service;

	@Mock
	private ProductGateway gateway;

	@Test
	public void testFindAllProdutos() {
		//Dado uma lista de produtos existentes (given)
		List<Product> listDeProdutosMock = getProductListMock();
		when(this.gateway.findAll()).thenReturn(listDeProdutosMock);

		//Quando estes produtos forem consultados (when)
		List<Product> produtosEncontrados = this.service.findAll();

		//Entao os produtos deverão ser retornados (then)
		assertThat(produtosEncontrados).isNotNull().isNotEmpty();
		assertEquals(produtosEncontrados.size(), listDeProdutosMock.size());
	}

	@Test
	public void testFindById() {
		Product produto = this.getProduct();

		when(this.gateway.findById(1l)).thenReturn(Optional.of(produto));

		assertThat(this.service.findById(1l)).contains(produto);
	}

	@Test
	public void testCreate() {
		//Comparando tamanho da lista depois de executar o método save
//		assertEquals(3, this.produtos.size());
	}

	private List<Product> getProductListMock() {
		List<Product> produtos = new ArrayList<Product>();

		Product p1 = new Product("Produto 1", 1.5, 5);
		p1.setId(1l);
		produtos.add(p1);

		Product p2 = new Product("Produto 2", 3.5, 2);
		p2.setId(2l);
		produtos.add(p2);

		return produtos;
	}

	private Product getProduct() {
		Product p = new Product("Produto", 100.5, 10);
		p.setId(1l);

		return p;
	}
}
