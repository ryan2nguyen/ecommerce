package com.sotatek.rea.service;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sotatek.rea.app.AccountService;
import com.sotatek.rea.app.RetailService;
import com.sotatek.rea.infrastructure.repository.RetailRepository;
import com.sotatek.rea.ws.dto.ResponseDataDto;
import com.sotatek.rea.ws.dto.RetailDto;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RetailServiceTest {

	@Autowired
	private RetailService retailService;
	
	@MockBean
	private RetailRepository retailRepository;
	
	@Test
	void add_case1() {
		
	}
	
	@Test
	void add_case2() {
		
	}
	
	@Test
	void findByRetailId_case1() {
		
	}
	
	@Test
	void findByRetailId_case2() {
		
	}
}
