package com.sotatek.order.app.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.sotatek.order.app.external.dto.OrderDto;
import com.sotatek.order.ws.dto.ResponseDataDto;

import feign.Headers;
import feign.RequestLine;

//@FeignClient(name = "pre-deposited-account")
//public interface PreDepositedAccountClientService {
//
//	
//	@RequestLine("POST /pay-order")
//    @Headers("Content-Type: application/json")
//	public ResponseDataDto<?> payOrder(@RequestHeader("userId") String userId, OrderDto orderDto);
//}

@FeignClient(name = "pre-deposited-account")
public interface PreDepositedAccountClientService {

	@PostMapping(value = "/account/pay-order")
	@Headers("Content-Type: application/json")
	public ResponseDataDto<?> payOrder(@RequestHeader("userId") String userId, OrderDto orderDto);
}