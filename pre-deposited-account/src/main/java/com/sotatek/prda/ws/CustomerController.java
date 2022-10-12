package com.sotatek.prda.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sotatek.prda.app.CustomerService;
import com.sotatek.prda.ws.dto.AccountDto;
import com.sotatek.prda.ws.dto.CustomerDto;
import com.sotatek.prda.ws.dto.ResponseDataDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseDataDto<?> customerAdd(@RequestBody List<CustomerDto> customers) throws Exception {
        return customerService.add(customers);
    }
	
	@RequestMapping(value = "deposit", method = RequestMethod.POST)
    public ResponseDataDto<?> deposit(@RequestHeader(value="userId") String userId, @RequestBody AccountDto request) throws Exception {
		return customerService.deposit(request, Long.parseLong(userId));
    }
}
