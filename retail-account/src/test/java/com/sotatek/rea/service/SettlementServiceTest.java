package com.sotatek.rea.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sotatek.rea.app.AccountService;
import com.sotatek.rea.app.RetailService;
import com.sotatek.rea.app.SettlementService;
import com.sotatek.rea.app.external.OrderClientService;
import com.sotatek.rea.app.external.RetailInventoryClientService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SettlementServiceTest {

	@Autowired
	private SettlementService settlementService;
	
	@MockBean
	private AccountService accountService;
	
	@MockBean
	private OrderClientService orderClientService;
	
	@MockBean
	private RetailInventoryClientService retailInventoryClientService;
	
	@Test
	void jobHandle_case1() {
		
	}
	
	@Test
	void jobHandle_case2() {
		
	}
}
