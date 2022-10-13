package com.sotatek.order.app.external.dto;

import lombok.Builder;

@Builder
public class ProductDto {

	public Long productId;
	
	public Integer quantity;
	
	public Long price;
	
	public Long retailId;
}
