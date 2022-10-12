package com.sotatek.order.app;

import java.util.List;

import com.sotatek.order.ws.dto.OrderDetailDto;
import com.sotatek.order.ws.dto.OrderDto;
import com.sotatek.order.ws.dto.ResponseDataDto;

public interface OrderService {
	
	public ResponseDataDto<?> callbackState(OrderDto orderDto);
	
	public ResponseDataDto<?> findForSettlement();
	
	public ResponseDataDto<?> createOrder(List<OrderDetailDto> orderDetails, Long customerId);
	
	

}
