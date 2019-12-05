package com.nalidao.shopchart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nalidao.shopchart.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
