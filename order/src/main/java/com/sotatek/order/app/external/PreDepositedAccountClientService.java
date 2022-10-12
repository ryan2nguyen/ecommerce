package com.sotatek.order.app.external;

import org.springframework.web.bind.annotation.RequestHeader;

import com.sotatek.order.app.external.dto.OrderDto;
import com.sotatek.order.ws.dto.ResponseDataDto;

import feign.Headers;
import feign.RequestLine;

public interface PreDepositedAccountClientService {

	
	@RequestLine("POST /pay-order")
    @Headers("Content-Type: application/json")
	public ResponseDataDto<?> payOrder(@RequestHeader("userId") String userId, OrderDto orderDto);
}
