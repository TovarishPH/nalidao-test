package com.nalidao.shopchart.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Customer {

	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
}
