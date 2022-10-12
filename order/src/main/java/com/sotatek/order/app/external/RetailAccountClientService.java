package com.sotatek.order.app.external;

import java.util.List;

import com.sotatek.order.app.external.dto.AccountDto;
import com.sotatek.order.ws.dto.ResponseDataDto;

import feign.Headers;
import feign.RequestLine;

public interface RetailAccountClientService {

	@RequestLine("POST /account/receive-amount")
    @Headers("Content-Type: application/json")
	public ResponseDataDto<?> receiveAmount(List<AccountDto> request);
}
