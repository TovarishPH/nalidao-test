package com.nalidao.shopchart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nalidao.shopchart.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
