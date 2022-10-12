package com.sotatek.order.app.external.dto;

import lombok.Builder;

@Builder
public class OrderDto {

	public Long totalAmount;
	
	public Long orderId;
}
