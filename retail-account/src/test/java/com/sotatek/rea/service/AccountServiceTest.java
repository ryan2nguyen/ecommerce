package com.sotatek.rea.service;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sotatek.rea.app.AccountService;
import com.sotatek.rea.infrastructure.repository.AccountHistoryRepository;
import com.sotatek.rea.infrastructure.repository.AccountRepository;
import com.sotatek.rea.infrastructure.repository.RetailRepository;
import com.sotatek.rea.ws.dto.AccountDto;
import com.sotatek.rea.ws.dto.ResponseDataDto;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTest {
	
	@Autowired
	private AccountService accountService;
	
	@MockBean
	private RetailRepository retailRepository;
	
	@MockBean
	private AccountRepository accountRepository;
	
	@MockBean
	private AccountHistoryRepository accountHistoryRepository;
	
	@Test
	void receiveAmount_case1() {
		
	}
	
	@Test
	void receiveAmount_case2() {
		
	}
	
	@Test
	void findForSettlement_case1() {
		
	}
	
	@Test
	void findForSettlement_case2() {
		
	}
}
