package com.nalidao.products.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nalidao.products.controller.dto.ProductDto;
import com.nalidao.products.domain.Product;
import com.nalidao.products.errorhandling.exception.ProductNotFoundException;
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
		
		ResultActions result = mockMvc.perform(get("/product-api/{id}", id)
										.accept(MediaType.APPLICATION_JSON)
										.content(this.mapper.writeValueAsString(product)));
				result.andDo(print())
						.andExpect(status().isOk());
	
	}
	
	@Test
	public void testFindProductByIdReturnsNotFound() throws Exception {
		long id = 1;
		
		when(this.service.findById(id)).thenThrow(ProductNotFoundException.class);
		
		ResultActions result = mockMvc.perform(get("/product-api/{id}", id));
				result.andDo(print())
						.andExpect(status().isNotFound());
	
	}
	
	@Test
	public void testCreateProductReturnsOk() throws Exception {
		ProductDto form = this.utils.getBodyForm();
		
		ResultActions result = mockMvc.perform(post("/product-api")
										.contentType(MediaType.APPLICATION_JSON)
										.content(this.mapper.writeValueAsString(form)));
		
		result.andDo(print())
				.andExpect(status().isCreated())
				.andReturn();
		
	}
	
	@Test
	public void testCreatProductInvalidParameterReturnsBadRequest() throws Exception {
		ProductDto form = new ProductDto(null, 10.0, 5);
		
		ResultActions result = mockMvc.perform(post("/product-api")
										.contentType(MediaType.APPLICATION_JSON)
										.content(this.mapper.writeValueAsString(form)));
		
		result.andDo(print())
				.andExpect(status().isBadRequest())
				.andReturn();
	}
	
	@Test
	public void testRemoveProductReturnsOk() throws Exception {
		long id = 1;
		ResultActions result = mockMvc.perform(delete("/product-api/{id}", id)
										.contentType(MediaType.APPLICATION_JSON));
		
		result.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
	}
	
}
