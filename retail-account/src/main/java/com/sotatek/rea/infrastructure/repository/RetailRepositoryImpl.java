package com.sotatek.rea.infrastructure.repository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sotatek.rea.domain.Account;
import com.sotatek.rea.domain.AccountHistory;
import com.sotatek.rea.domain.Retail;
import com.sotatek.rea.infrastructure.entity.RetailEntity;
import com.sotatek.rea.infrastructure.jpa.JpaRetailRepository;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class RetailRepositoryImpl implements RetailRepository{
	
	@Autowired
	private JpaRetailRepository jpaRetailRepository;
	
	@Override
	public Stream<Retail> getAll() {
		return jpaRetailRepository.getAll().map(retail -> toDomain(retail));
	}

	@Override
	public Retail findByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Retail save(Retail retail) {
		// TODO Auto-generated method stub
		return toDomain(jpaRetailRepository.save(RetailEntity.builder().email(retail.email).name(retail.name).phone(retail.phone).token(retail.token).build()));
	}
	
	@Override
	public Retail findById(Long retailId) {
		return toDomain(jpaRetailRepository.findById(retailId).get());
	}

	private Retail toDomain(RetailEntity retailEntity) {
		return Retail.builder()
				   .id(retailEntity.id)
				   .email(retailEntity.email)
				   .name(retailEntity.name)
				   .phone(retailEntity.phone)
				   .accounts(retailEntity.accountEntityies.stream()
				      .map(account -> Account.builder()
				    		  .balance(account.balance)
				    		  .build()
					  ).collect(Collectors.toList())
				   )
				   .accountHistories(retailEntity.accountHistoryEntityies.stream()
					   .map(history -> AccountHistory.builder()
							  .amount(history.amount)
							  .createTime(history.createTime)
							  .orderId(history.orderId).build())
					   .collect(Collectors.toList())
					)
				   .build();
	}

}
