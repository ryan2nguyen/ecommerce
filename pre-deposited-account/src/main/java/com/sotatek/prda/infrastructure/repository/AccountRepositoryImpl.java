package com.sotatek.prda.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sotatek.prda.domain.Account;
import com.sotatek.prda.infrastructure.entity.AccountEntity;
import com.sotatek.prda.infrastructure.jpa.JpaAccountRepository;
import com.sotatek.prda.infrastructure.jpa.JpaCustomerRepository;

@Component
public class AccountRepositoryImpl implements AccountRepository{

	@Autowired
	private JpaAccountRepository jpaAccountRepository;
	
	@Autowired
	private JpaCustomerRepository jpaCustomerRepository;
	
	@Override
	public Account findByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return toDomain(jpaAccountRepository.findByCustomerId(customerId));
	}

	@Override
	public Account save(Account account) {
		return toDomain(jpaAccountRepository.save(
			       AccountEntity.builder()
			       .balance(account.balance)
			       .customer(jpaCustomerRepository.findById(account.customer.id).get())
			       .build()
			   ));
	}
	
	private Account toDomain(AccountEntity accountEntity) {
		return Account.builder()
				.balance(accountEntity.balance)
				.build();
	}

}
