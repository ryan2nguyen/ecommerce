package com.sotatek.order.app.external.dto;

import lombok.Builder;

@Builder
public class AccountRetailDto {

    public Long retailId;
	
	public Long amount;
	
	public Long orderId;
}
