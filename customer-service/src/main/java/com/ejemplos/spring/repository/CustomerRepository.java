package com.ejemplos.spring.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplos.spring.entity.Customer;
import com.ejemplos.spring.entity.Region;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByNumberID(String numberID);
    public List<Customer> findByLastName(String lastName);
    public List<Customer> findByRegion(Region region);
}
