package com.sotatek.order.app.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.sotatek.order.app.external.dto.AccountDto;
import com.sotatek.order.ws.dto.ResponseDataDto;

import feign.Headers;
import feign.RequestLine;

@FeignClient(name = "retail-account")
public interface RetailAccountClientService {

	@PostMapping(value = "/account/receive-amount")
	@Headers("Content-Type: application/json")
	public ResponseDataDto<?> receiveAmount(List<AccountDto> request);
}
