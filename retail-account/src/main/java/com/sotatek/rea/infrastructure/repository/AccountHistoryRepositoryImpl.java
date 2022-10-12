package com.sotatek.rea.infrastructure.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sotatek.rea.domain.AccountHistory;
import com.sotatek.rea.infrastructure.entity.AccountHistoryEntity;
import com.sotatek.rea.infrastructure.jpa.JpaAccountHistoryRepository;
import com.sotatek.rea.infrastructure.jpa.JpaRetailRepository;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class AccountHistoryRepositoryImpl implements AccountHistoryRepository {
	
	@Autowired
	private JpaAccountHistoryRepository jpaAccountHistoryRepository;
	
	@Autowired
	private JpaRetailRepository jpaRetailRepository;
	
	@Override
	public AccountHistory save(AccountHistory accountHistory) {
		return toDomain(jpaAccountHistoryRepository.save(
			       AccountHistoryEntity.builder()
			       .amount(accountHistory.amount)
			       .createTime(new Date())
			       .retail(jpaRetailRepository.findById(accountHistory.retail.id).get())
			       .type(accountHistory.type)
			       .orderId(accountHistory.orderId)
			       .build()
			   ));
	}
	
	private AccountHistory toDomain(AccountHistoryEntity entity) {
		return AccountHistory.builder()
			       .amount(entity.amount)
			       .createTime(new Date())
			       .type(entity.type)
			       .orderId(entity.orderId)
			       .build();
	}

	
}
