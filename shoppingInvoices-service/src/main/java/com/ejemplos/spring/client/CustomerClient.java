package com.ejemplos.spring.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ejemplos.spring.model.Customer;

//@FeignClient(name = "customer-service")
//@FeignClient(name = "customer-service", url = "localhost:8092") //se usa si no esta habilitado para que se registre en eureka
//@FeignClient(name = "customer-service", fallback = CustomerHystrixFallbackFactory.class)
@FeignClient(name = "customer-service", fallback = CustomerHystrixFallbackFactory.class)
//@RequestMapping("/customers") al implementar la clase del fallback se elimina este
//requestmapping y se añade al getmapping del metodo
public interface CustomerClient {

	@GetMapping(value = "/customers/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Integer id);
}
