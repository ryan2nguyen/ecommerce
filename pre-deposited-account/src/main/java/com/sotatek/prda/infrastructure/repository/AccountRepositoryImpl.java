package com.sotatek.prda.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sotatek.prda.domain.Account;
import com.sotatek.prda.domain.Customer;
import com.sotatek.prda.infrastructure.entity.AccountEntity;
import com.sotatek.prda.infrastructure.entity.CustomerEntity;
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
		System.out.println(jpaAccountRepository.findByCustomerId(customerId));
		return toDomain(jpaAccountRepository.findByCustomerId(customerId));
	}

	@Override
	public Account save(Account account) {
		return toDomain(jpaAccountRepository.save(
			       AccountEntity.builder()
			       .id(account.id)
			       .balance(account.balance)
			       .customer(CustomerEntity.builder().id(account.customer.id).build())
			       .build()
			   ));
	}
	
	private Account toDomain(AccountEntity accountEntity) {
		if(accountEntity == null) return null;
		return Account.builder()
				.id(accountEntity.id)
				.balance(accountEntity.balance)
				.customer(Customer.builder()
						.id(accountEntity.customer.id)
						.email(accountEntity.customer.email)
						.name(accountEntity.customer.name)
						.phone(accountEntity.customer.phone)
						.token(accountEntity.customer.token)
						.build()
				)
				.build();
	}

}
