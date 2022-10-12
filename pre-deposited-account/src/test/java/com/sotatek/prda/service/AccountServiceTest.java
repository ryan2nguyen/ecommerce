package com.sotatek.prda.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sotatek.prda.app.AccountService;
import com.sotatek.prda.infrastructure.repository.AccountHistoryRepository;
import com.sotatek.prda.infrastructure.repository.AccountRepository;
import com.sotatek.prda.infrastructure.repository.CustomerRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	private AccountRepository accountRepository;
	
	@MockBean
	private AccountHistoryRepository accountHistoryRepository;
	
	@Test
	void payOrder_case1() {
		
	}
	
	@Test
	void payOrder_case2() {
		
	}
}
