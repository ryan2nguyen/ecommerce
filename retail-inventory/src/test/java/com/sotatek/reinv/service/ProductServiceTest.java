package com.sotatek.reinv.service;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sotatek.reinv.app.ProductService;
import com.sotatek.reinv.app.external.RetailAccountClientService;
import com.sotatek.reinv.infrastructure.repository.ProductHistoryReposity;
import com.sotatek.reinv.infrastructure.repository.ProductReposity;
import com.sotatek.reinv.ws.dto.ProductDto;
import com.sotatek.reinv.ws.dto.ResponseDataDto;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	
	@MockBean
	private ProductReposity productReposity;
	
	@MockBean
	private ProductHistoryReposity productHistoryReposity;
	
	@MockBean
	private RetailAccountClientService retailAccountClientService;
	
	@Test
	void add_case1() {
		
	}
	
	@Test
	void add_case2() {
		
	}
	
	@Test
	void increateInventory_case1() {
		
	}
	
	@Test
	void increateInventory_case2() {
		
	}
	
	@Test
	void fetchPriceById_case1() {
		
	}
	
	@Test
	void fetchPriceById_case2() {
		
	}
	
	@Test
	void deductInventory_case1() {
		
	}
	
	@Test
	void deductInventory_case2() {
		
	}
}
