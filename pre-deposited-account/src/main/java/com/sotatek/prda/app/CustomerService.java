package com.sotatek.prda.app;

import java.util.List;

import com.sotatek.prda.ws.dto.AccountDto;
import com.sotatek.prda.ws.dto.CustomerDto;
import com.sotatek.prda.ws.dto.ResponseDataDto;

public interface CustomerService {
	
	public ResponseDataDto<?> deposit(AccountDto accountDto, Long customerId);
	
	public ResponseDataDto<?> add(List<CustomerDto> customers);
	
	
}
