package com.nalidao.products.service;

import com.nalidao.products.domain.Product;
import com.nalidao.products.errorhandling.exception.ProductNotFoundException;
import com.nalidao.products.gateway.ProductGateway;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductGateway gateway;

	@Test
	public void testFindAllProducts() {
		//Dado uma lista de produtos existentes (given)
		List<Product> mockList = getProductListMock();
		when(this.gateway.findAll()).thenReturn(mockList);

		//Quando estes produtos forem consultados (when)
		List<Product> foundList = this.service.findAll();

		//Entao os produtos deverão ser retornados (then)
		assertThat(foundList).isNotNull().isNotEmpty();
		assertEquals(foundList.size(), mockList.size());
		
		assertEquals(foundList.get(1).getId(), mockList.get(1).getId());
		assertEquals(foundList.get(1).getName(), mockList.get(1).getName());
		assertEquals(foundList.get(1).getPrice(), mockList.get(1).getPrice());
		assertEquals(foundList.get(1).getAmount(), mockList.get(1).getAmount());
	}

	@Test
	public void testFindById() {
		Product product = this.getProduct();

		when(this.gateway.findById(1l)).thenReturn(Optional.of(product));
		
		Product found = this.gateway.findById(1l).get();

		assertThat(this.service.findById(1l)).contains(product);
		assertSame(found, product);
	}
	
	@Test
	public void testFindByIdNotFoundException() {
		Throwable thrown = ThrowableAssert.catchThrowable(() -> {
			this.service.findById(1l);
		});
		
		assertThat(thrown).isInstanceOf(ProductNotFoundException.class).hasMessage("Product id: 1 not found.");
		
		//Isso também funciona
		Assertions.assertThrows(ProductNotFoundException.class, () -> {
			this.service.findById(1l);
		}, "Different exception launched.");
		
	}

	@Test
	public void testCreate() {
		//Comparando tamanho da lista depois de executar o método save
//		Product product = this.getProduct();
//		Assertions.assertThrows(null, () -> {
//			this.service.save(product);
//		}, "Different exception launched.");
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
