package com.sotatek.order.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

	public Long orderId;
	
	public String state;
	
	public Long totalAmount;
	
}
