package com.sotatek.order.app.external.dto;

import lombok.Builder;

@Builder
public class AccountDto {

    public Long retailId;
	
	public Long amount;
	
	public Long orderId;
}
