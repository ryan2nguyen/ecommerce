package com.sotatek.rea.app;

import java.util.List;

import com.sotatek.rea.ws.dto.AccountDto;
import com.sotatek.rea.ws.dto.ResponseDataDto;
import com.sotatek.rea.ws.dto.RetailDto;
import com.sotatek.rea.ws.dto.SettlementDto;

public interface AccountService {

    public ResponseDataDto<?> receiveAmount(List<AccountDto> request);
    
    public List<SettlementDto> findForSettlement();
}
