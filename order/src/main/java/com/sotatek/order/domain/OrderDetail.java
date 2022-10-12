package com.sotatek.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    public Long price;
	
    public Long productId;
	
    public Integer quantity;
}
