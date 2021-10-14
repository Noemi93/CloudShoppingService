package com.ejemplos.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplos.spring.entity.Customer;
import com.ejemplos.spring.entity.Region;
import com.ejemplos.spring.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{
	
	private final CustomerRepository customerRepository;
	
	@Override
	public List<Customer> findCustomerAll() {
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> findCustomersByRegion(Region region) {
		return customerRepository.findByRegion(region);
	}

	//ahora este metodo si es idempotente porque se asegura de que solo se 
	//guarde un nuevo cliente si no existe 
	@Override
	public Customer createCustomer(Customer customer) {
		Customer customerDB = customerRepository.findByNumberID(customer.getNumberID());
        if (customerDB != null){
            return  customerDB;
        }

        customer.setState("CREATED");
        customerDB = customerRepository.save ( customer );
        return customerDB;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer customerDB = getCustomer(customer.getId());
		if(customerDB == null) {
			return null;
		}
		
		customerDB.setFirstName(customer.getFirstName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoUrl(customer.getPhotoUrl());
		return customerRepository.save(customerDB);
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		Customer customerDB = getCustomer(customer.getId());
		if(customerDB == null) {
			return null;
		}
		customer.setState("DELETED");
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(Integer id) {
		return customerRepository.findById(id).orElse(null);
	}

}
