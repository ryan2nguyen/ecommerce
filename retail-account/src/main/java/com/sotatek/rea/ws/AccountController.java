package com.sotatek.rea.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sotatek.rea.app.AccountService;
import com.sotatek.rea.ws.dto.AccountDto;
import com.sotatek.rea.ws.dto.ResponseDataDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @RequestMapping(value = "receive-amount", method = RequestMethod.POST)
    public ResponseDataDto<?> receiveAmount(@RequestBody List<AccountDto> request) throws Exception {
        return accountService.receiveAmount(request);
    }
}
