package com.ejemplos.spring.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ejemplos.spring.model.Product;

@FeignClient(name = "service-product")
//@FeignClient(name = "service-product", url = "localhost:8091") //se usa si no esta habilitado para que se registre en eureka
@RequestMapping(value = "/products")
public interface ProductClient {

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id);
	
	@GetMapping(value = "/{id}/stock")
	public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Integer id, 
			@RequestParam(name = "quantity", required = true) Double quantity);
}