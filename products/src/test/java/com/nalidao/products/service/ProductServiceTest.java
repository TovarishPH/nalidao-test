package com.nalidao.products.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nalidao.products.domain.Product;
import com.nalidao.products.errorhandling.exception.ProductNotFoundException;
import com.nalidao.products.gateway.ProductGateway;
import com.nalidao.products.test.util.TestUtils;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductGateway gateway;
	
	private TestUtils utils = new TestUtils();

	@Test
	public void testFindAllProducts() {
		//Dado uma lista de produtos existentes (given)
		List<Product> mockList = this.utils.getProductListMock();
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
		Product product = this.utils.getProduct();
		long id = 1l;

		when(this.gateway.findById(id)).thenReturn(Optional.of(product));
		
		Product found = this.gateway.findById(1l).get();

		assertThat(this.service.findById(id)).contains(product);
		assertSame(product, found);
	}
	
	@Test
	public void testFindByIdThrowsNotFoundException() {
		Throwable thrown = ThrowableAssert.catchThrowable(() -> {
			this.service.findById(1l);
		});
		
		assertThat(thrown).isInstanceOf(ProductNotFoundException.class)
							.hasMessage("Product id: 1 not found.");
		
		//Isso também funciona
		Assertions.assertThrows(ProductNotFoundException.class, () -> {
			this.service.findById(1l);
		}, "Different exception launched.");
		
	}

	@Test
	public void testCreate() {
		Product product = this.utils.getProduct();
		
		this.service.save(product);
		Mockito.verify(this.gateway).save(product);
	}
	
	@Test
	public void testUpdate() {
		Product product = this.utils.getProduct();
		
		when(this.gateway.findById(1l)).thenReturn(Optional.of(product));
		
		this.service.update(1l, product);
		Mockito.verify(this.gateway).save(product);
	}

	@Test
	public void testUpdateThrowNotFoundException() {
		long id = 1l;
		when(this.gateway.findById(id)).thenReturn(Optional.empty());
		
		Throwable thrown = ThrowableAssert.catchThrowable(() -> {
			this.service.update(1l, new Product());
		});
		
		assertThat(thrown).isInstanceOf(ProductNotFoundException.class)
							.hasMessage("Update not accomplished. Product id: " + id + " not found.");
	}
	
	@Test
	public void testRemoveProduct() {
		Product product = this.utils.getProduct();
		
		when(this.gateway.findById(1l)).thenReturn(Optional.of(product));
		this.service.remove(1l);
		Mockito.verify(this.gateway).deleteById(1l);
	}
	
	@Test
	public void testRemoveThrowNotFoundException() {
		when(this.gateway.findById(1l)).thenReturn(Optional.empty());
		
		Throwable thrown = ThrowableAssert.catchThrowable(() -> {
			this.service.remove(1l);
		});
		
		assertThat(thrown).isInstanceOf(ProductNotFoundException.class)
							.hasMessage("Delete not accomplished. Product id 1 not found in our data base.");
	}

}
