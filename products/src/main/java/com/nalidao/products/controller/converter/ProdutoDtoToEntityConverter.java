package com.nalidao.products.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.products.controller.dto.ProdutoDto;
import com.nalidao.products.domain.Produto;

@Component
public class ProdutoDtoToEntityConverter implements Converter<ProdutoDto, Produto> {

	@Override
	public Produto convert(ProdutoDto dto) {
		return new Produto(dto.getNome(), dto.getPreco(), dto.getQuantidade());
	}

}
