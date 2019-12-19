package com.nalidao.shopchart.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nalidao.shopchart.domain.ShopChart;

@Repository
public interface ShopChartRepository extends MongoRepository<ShopChart, BigInteger> {

}
