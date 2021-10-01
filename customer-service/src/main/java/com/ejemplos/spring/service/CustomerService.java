package com.ejemplos.spring.service;

import java.util.List;

import com.ejemplos.spring.entity.Customer;
import com.ejemplos.spring.entity.Region;

public interface CustomerService {

	public List<Customer> findCustomerAll();
    public List<Customer> findCustomersByRegion(Region region);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public Customer getCustomer(Integer id);
}
