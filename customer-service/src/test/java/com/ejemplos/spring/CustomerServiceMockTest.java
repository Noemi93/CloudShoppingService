package com.ejemplos.spring;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ejemplos.spring.entity.Customer;
import com.ejemplos.spring.entity.Region;
import com.ejemplos.spring.repository.CustomerRepository;
import com.ejemplos.spring.service.CustomerService;
import com.ejemplos.spring.service.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceMockTest {

	
	@Mock
	private CustomerRepository customerRepo;
	
	private CustomerService customerService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		customerService = new CustomerServiceImpl(customerRepo);
		
		Customer customerA = Customer.builder()
				.id(1)
				.numberID("001")
				.firstName("Lola")
				.lastName("Campos")
				.email("lolita@gmail.com")
				.photoUrl("")
				.region(Region.builder().id(5).build())
				.state("CREATED")
				.build();
				
		Mockito.when(customerRepo.findById(1)).thenReturn(Optional.of(customerA));
		Mockito.when(customerRepo.save(customerA)).thenReturn(customerA);
	}
	
	@Test
	public void whenValidGetID_ThenReturnCustomer() {
		Customer found = customerService.getCustomer(1);
		Assertions.assertThat(found.getFirstName()).isEqualTo("Lola");
	}
	
	
	
	
}
