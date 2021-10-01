package com.ejemplos.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplos.spring.entity.InvoiceItem;

@Repository
public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Integer>{

	
}
