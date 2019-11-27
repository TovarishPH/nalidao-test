package com.nalidao.products.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;
import java.util.Optional;

import javax.net.ssl.SSLEngineResult.Status;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nalidao.products.domain.Product;
import com.nalidao.products.service.ProductService;
import com.nalidao.products.test.util.TestUtils;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

	@MockBean
	private ProductService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	private TestUtils utils = new TestUtils();
	
	@Test
	public void testWhenListHasProductReturnsOk() throws Exception {
		List<Product> list = this.utils.getProductListMock();
		
		when(this.service.findAll()).thenReturn(list);
		
		mockMvc.perform(get("/product-api")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void testFindProductByIdReturnsOk() throws Exception {
		Product product = this.utils.getProduct();
		long id = 1;
		
		when(this.service.findById(id)).thenReturn(Optional.of(product));
		
		mockMvc.perform(get("/product-api/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(product)))
				.andDo(print())
				.andExpect(status().isOk());
	
	}
}
