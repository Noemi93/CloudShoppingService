package com.ejemplos.spring.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

	private Integer id;
	private String name;
	private String description;
	private Double stock;
	private Double price;
	private String status;
	private Category category;
}
