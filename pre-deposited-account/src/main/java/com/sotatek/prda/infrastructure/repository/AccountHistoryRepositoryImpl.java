package com.sotatek.prda.infrastructure.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sotatek.prda.domain.AccountHistory;
import com.sotatek.prda.infrastructure.entity.AccountEntity;
import com.sotatek.prda.infrastructure.entity.AccountHistoryEntity;
import com.sotatek.prda.infrastructure.jpa.JpaAccountHistoryRepository;
import com.sotatek.prda.infrastructure.jpa.JpaCustomerRepository;

@Component
public class AccountHistoryRepositoryImpl implements AccountHistoryRepository{

    @Autowired
    private JpaAccountHistoryRepository jpaAccountHistoryRepository;
    
    @Autowired
    private JpaCustomerRepository jpaCustomerRepository;
    
    @Override
    public AccountHistory save(AccountHistory accountHistory) {
        return toDomain(jpaAccountHistoryRepository.save(
                   AccountHistoryEntity.builder()
                   .id(accountHistory.id)
                   .amount(accountHistory.amount)
                   .createTime(new Date())
                   .customer(jpaCustomerRepository.findById(accountHistory.customer.id).get())
                   .type(accountHistory.type)
                   .orderId(accountHistory.orderId)
                   .build()
               ));
    }
    
    private AccountHistory toDomain(AccountHistoryEntity entity) {
        if(entity == null) return null;
        return AccountHistory.builder()
                   .id(entity.id)
                   .amount(entity.amount)
                   .createTime(new Date())
                   .type(entity.type)
                   .orderId(entity.orderId)
                   .build();
    }

}
