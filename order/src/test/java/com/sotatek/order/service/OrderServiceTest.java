package com.sotatek.order.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sotatek.order.app.OrderService;
import com.sotatek.order.app.external.PreDepositedAccountClientService;
import com.sotatek.order.app.external.RetailAccountClientService;
import com.sotatek.order.app.external.RetailInventoryClientService;
import com.sotatek.order.infrastructure.repository.OrderRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	@MockBean
	private OrderRepository orderRepository;
	
	@MockBean
	private PreDepositedAccountClientService preDepositedAccountClientService;
	
	@MockBean
	private RetailAccountClientService retailAccountClientService;
	
	@MockBean
	private RetailInventoryClientService retailInventoryClientService;
	
//	Long orderId = null;
//	String state = "";
//	
//	when(orderRepository.findById(any()))
//	  .thenReturn(Optional.of(new com.sotatek.order.infrastructure.OrderEntity.Order()));
//	
//	when(orderRepository.save(any()))
//	  .thenReturn(new com.sotatek.order.infrastructure.OrderEntity.Order());
//	
//	ResponseData<?> result = callbackStateService.callbackState(orderId, state);
//	assertThat(result.code).isEqualTo(HttpStatus.BAD_REQUEST);
	
	@Test
	void findForSettlement_case1() {
		
	}
	
	@Test
	void findForSettlement_case2() {
		
	}
	
	@Test
	void createOrder_case1() {
		
	}
	
	@Test
	void createOrder_case2() {
		
	}
}
