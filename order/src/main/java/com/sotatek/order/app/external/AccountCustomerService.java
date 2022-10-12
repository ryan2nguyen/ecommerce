package com.sotatek.order.app.external;

import java.util.Map;

public interface AccountCustomerService {
	
	public Map<String, Object> payOrder(Long customerId, Long totalAmount) throws Exception;
}
