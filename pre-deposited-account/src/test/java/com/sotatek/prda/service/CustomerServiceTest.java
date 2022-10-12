package com.sotatek.prda.service;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sotatek.prda.app.CustomerService;
import com.sotatek.prda.infrastructure.repository.AccountHistoryRepository;
import com.sotatek.prda.infrastructure.repository.AccountRepository;
import com.sotatek.prda.infrastructure.repository.CustomerRepository;
import com.sotatek.prda.ws.dto.AccountDto;
import com.sotatek.prda.ws.dto.CustomerDto;
import com.sotatek.prda.ws.dto.ResponseDataDto;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	private AccountRepository accountRepository;
	
	@MockBean
	private AccountHistoryRepository accountHistoryRepository;
	
	@Test
	void deposit_case1() {
		
	}
	
	@Test
	void deposit_case2() {
		
	}
	
	@Test
	void add_case1() {
		
	}
	
	@Test
	void add_case2() {
		
	}
}
