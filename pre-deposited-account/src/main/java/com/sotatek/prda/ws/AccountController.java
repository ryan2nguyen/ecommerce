package com.sotatek.prda.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sotatek.prda.app.AccountService;
import com.sotatek.prda.ws.dto.OrderDto;
import com.sotatek.prda.ws.dto.ResponseDataDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "pay-order", method = RequestMethod.POST)
    public ResponseDataDto<?> payOrder(@RequestHeader(value="userId") String userId, @RequestBody OrderDto request) throws Exception {
		return accountService.payOrder(request, Long.parseLong(userId));
    }
}
