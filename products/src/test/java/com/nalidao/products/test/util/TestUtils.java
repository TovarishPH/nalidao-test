package com.nalidao.products.test.util;

import java.util.ArrayList;
import java.util.List;

import com.nalidao.products.domain.Product;

public class TestUtils {

	public List<Product> getProductListMock() {
		List<Product> produtos = new ArrayList<Product>();

		Product p1 = new Product("Produto 1", 1.5, 5);
		p1.setId(1l);
		produtos.add(p1);

		Product p2 = new Product("Produto 2", 3.5, 2);
		p2.setId(2l);
		produtos.add(p2);

		return produtos;
	}

	public Product getProduct() {
		Product p = new Product("Produto", 100.5, 10);
		p.setId(1l);

		return p;
	}
}
