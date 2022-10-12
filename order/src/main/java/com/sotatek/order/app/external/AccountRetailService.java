package com.sotatek.order.app.external;

import java.util.List;
import java.util.Map;

import com.sotatek.order.app.external.dto.ProductDto;

public interface AccountRetailService {
	
	public Object receiveAmount(List<ProductDto> orders, Long orderId) throws Exception;
}
