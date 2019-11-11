package com.nalidao.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nalidao.products.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
