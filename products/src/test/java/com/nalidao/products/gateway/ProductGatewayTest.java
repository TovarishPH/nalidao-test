package com.nalidao.products.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nalidao.products.domain.Product;
import com.nalidao.products.repository.ProductRepository;
import com.nalidao.products.test.util.TestUtils;

@ExtendWith(MockitoExtension.class)
public class ProductGatewayTest {

	@InjectMocks
	private ProductGateway gateway;

	@Mock
	private ProductRepository repository;

	private TestUtils utils = new TestUtils();

	@Test
	public void testFindAllProducts() {
		List<Product> mockList = this.utils.getProductListMock();
		when(this.repository.findAll()).thenReturn(mockList);
		
		List<Product> list = this.gateway.findAll();
		
		assertThat(list).isNotNull().isNotEmpty();
		assertEquals(list.size(), mockList.size());
		assertSame(mockList, list);
		
	}
	
	@Test
	public void testFindProductById() {
		Product product = this.utils.getProduct();
		long id = 1l;
		
		when(this.repository.findById(id)).thenReturn(Optional.of(product));
		
		Optional<Product> found = this.gateway.findById(id);
		
		assertThat(found).isNotNull().isNotEmpty();
		assertSame(found.get(), product);
	}
	
	@Test
	public void testSaveProduct() {
		Product product = this.utils.getProduct();
		
		this.gateway.save(product);
		Mockito.verify(this.repository).save(product);
	}
	
	@Test
	public void testRemoveProduct() {
		long id = 1l;
		
		this.gateway.deleteById(id);
		Mockito.verify(this.repository).deleteById(id);
	}
}
