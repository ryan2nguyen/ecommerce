package com.sotatek.order.app.external.dto;

import lombok.Builder;

@Builder
public class ProductDto {

	public Long id;
	
	public Long price;
	
	public Integer quantity;
	
	public Long retailId;
}
