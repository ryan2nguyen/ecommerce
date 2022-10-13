package com.sotatek.rea.infrastructure.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sotatek.rea.domain.Account;
import com.sotatek.rea.domain.Retail;
import com.sotatek.rea.infrastructure.entity.AccountEntity;
import com.sotatek.rea.infrastructure.entity.RetailEntity;
import com.sotatek.rea.infrastructure.jpa.JpaAccountRepository;
import com.sotatek.rea.infrastructure.jpa.JpaRetailRepository;
import com.sotatek.rea.ws.dto.SettlementDto;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class AccountRepositoryImpl implements AccountRepository{
    
    @Autowired
    private JpaAccountRepository jpaAccountRepository;
    
    @Autowired
    private JpaRetailRepository jpaRetailRepository;
    
    @Override
    public List<SettlementDto> findByTypeAndCreateTime(String type, String createTime) {
        return jpaAccountRepository.findByTypeAndCreateTime(type, createTime);
    }

    @Override
    public Account findByRetailId(Long retailId) {
        // TODO Auto-generated method stub
        return toDomain(jpaAccountRepository.findByRetailId(retailId));
    }
    
    @Override
    public Account save(Account account) {
        // TODO Auto-generated method stub
        return toDomain(jpaAccountRepository.save(
                   AccountEntity.builder()
                   .id(account.id)
                   .balance(account.balance)
                   .retail(RetailEntity.builder().id(account.retail.id).build())
                   .build()
               ));
    }
    
    private Account toDomain(AccountEntity accountEntity) {
        if(accountEntity == null) return null;
        return Account.builder()
                .id(accountEntity.id)
                .balance(accountEntity.balance)
                .retail(Retail.builder().id(accountEntity.retail.id).build())
                .build();
    }

}
