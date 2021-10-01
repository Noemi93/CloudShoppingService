package com.ejemplos.spring;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ejemplos.spring.entity.Category;
import com.ejemplos.spring.entity.Product;
import com.ejemplos.spring.repository.ProductRepository;
import com.ejemplos.spring.service.ProductService;
import com.ejemplos.spring.service.ProductServiceImpl;

@SpringBootTest
public class ProductServiceMockTest {

	//En el otro test, se accedia a los datos de la bbdd
	//Ahora vamos a trabajar con datos creados aqui, asi que no hay que poner @Autowired, sino el @Mock 
	//ya que los datos seran mockeados
	@Mock
	private ProductRepository productRepository;
	
	private ProductService productService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		productService = new ProductServiceImpl(productRepository);
		
		Product computer =  Product.builder()
                .id(1)
                .name("computer")
                .category(Category.builder().id(1).build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();
		
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(computer));
		
		Mockito.when(productRepository.save(computer)).thenReturn(computer);
	}
	
	@Test
	public void whenValidGetID_ThenReturnProduct() {
		Product found = productService.getProduct(1);
		Assertions.assertThat(found.getName()).isEqualTo("computer");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//SE PUEDE PROBAR CON CUALQUIER DATO, CON ESTOS QUE NO ESTAN EN LA BBDD TAMBIEN SIRVE
//	@BeforeEach
//	public void setup() {
//		MockitoAnnotations.openMocks(this);
//		productService = new ProductServiceImpl(productRepository);
//		
//		Product computer =  Product.builder()
//                .id(5)
//                .name("adidas")
//                .category(Category.builder().id(5).build())
//                .price(Double.parseDouble("12.5"))
//                .stock(Double.parseDouble("5"))
//                .build();
//		
//		Mockito.when(productRepository.findById(5)).thenReturn(Optional.of(computer));
//	}
//	
//	@Test
//	public void whenValidGetID_ThenReturnProduct() {
//		Product found = productService.getProduct(5);
//		Assertions.assertThat(found.getName()).isEqualTo("adidas");
//	}
	
	@Test
	public void whenValidUpdateStock_ThenReturnNewStock() {
		
		Product newStock = productService.updateStock(1, Double.parseDouble("8"));
		Assertions.assertThat(newStock.getStock()).isEqualTo(13);
		
	}
	
}
