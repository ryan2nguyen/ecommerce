package com.sotatek.rea.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.rea.domain.Account;
import com.sotatek.rea.domain.AccountHistory;
import com.sotatek.rea.domain.Retail;
import com.sotatek.rea.infrastructure.repository.AccountHistoryRepository;
import com.sotatek.rea.infrastructure.repository.AccountRepository;
import com.sotatek.rea.infrastructure.repository.RetailRepository;
import com.sotatek.rea.ws.dto.AccountDto;
import com.sotatek.rea.ws.dto.ResponseDataDto;
import com.sotatek.rea.ws.dto.SettlementDto;

import kong.unirest.HttpStatus;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AccountServiceImpl implements AccountService{
    
    @Autowired
    private RetailRepository retailRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private AccountHistoryRepository accountHistoryRepository;
    
    @Override
    public ResponseDataDto<?> receiveAmount(List<AccountDto> request) {
        String result = "";
        if(request == null) {
            return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, "request doesn't exist");
        }
        for(AccountDto accountDto: request) {
            try {
                if(accountDto.retailId == null || accountDto.retailId <= 0) {
                    throw new Exception("retailId doesn't exist");
                }
                if(accountDto.amount == null || accountDto.amount <= 0) {
                    throw new Exception("amount doesn't exist");
                }
                if(accountDto.orderId == null || accountDto.orderId <= 0) {
                    throw new Exception("orderId doesn't exist");
                }
                Retail retail = retailRepository.findById(accountDto.retailId);
                if(retail == null) {
                    throw new Exception("Retail "+ accountDto.retailId +" doesn't exist");
                }
                
                Account account = accountRepository.findByRetailId(accountDto.retailId);
                if(account == null) {
                    account = accountRepository.save(
                            Account.builder()
                            .balance(0L)
                            .retail(retail)
                            .build()
                    );
                }
                if(account.balance == null) {
                    account.balance = 0L;
                }
                account.balance = account.balance + accountDto.amount;
                accountRepository.save(account);
                
                accountHistoryRepository.save(
                        AccountHistory.builder()
                        .amount(accountDto.amount)
                        .createTime(new Date())
                        .retail(retail)
                        .orderId(accountDto.orderId)
                        .type("receive")
                        .build()
                );
                
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                result += e.getMessage() + ". ";
            }
        }
        if(request.size() > 0 && result.isEmpty()) {
            return new ResponseDataDto<String>(HttpStatus.OK, "Done");
        }
        return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, result);
    }
    
    @Override
    public List<SettlementDto> findForSettlement() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
//            calendar.add(Calendar.DATE, -1);
            Date yesterday = calendar.getTime();
            return accountRepository.findByTypeAndCreateTime("receive", dateFormat.format(yesterday));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
