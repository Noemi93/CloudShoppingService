package com.ejemplos.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplos.spring.entity.Category;
import com.ejemplos.spring.entity.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

	public List<Product> findByCategory(Category category);
	
}
