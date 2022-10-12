package com.sotatek.prda.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sotatek.prda.domain.Account;
import com.sotatek.prda.domain.AccountHistory;
import com.sotatek.prda.domain.Customer;
import com.sotatek.prda.infrastructure.entity.CustomerEntity;
import com.sotatek.prda.infrastructure.jpa.JpaCustomerRepository;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private JpaCustomerRepository jpaCustomerRepository;
	
	@Override
	public Customer findById(Long customerId) {
		// TODO Auto-generated method stub
		return toDomain(jpaCustomerRepository.findById(customerId).get());
	}
	
	@Override
	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		return toDomain(jpaCustomerRepository.save(CustomerEntity.builder().email(customer.email).name(customer.name).phone(customer.phone).token(customer.token).build()));
	}
	
	@Override
	public Stream<Customer> getAll() {
		return jpaCustomerRepository.getAll().map(customer -> toDomain(customer));
	}
	
	private Customer toDomain(CustomerEntity customerEntity) {
		return Customer.builder()
				   .id(customerEntity.id)
				   .email(customerEntity.email)
				   .name(customerEntity.name)
				   .phone(customerEntity.phone)
				   .accounts(customerEntity.accountEntityies.stream()
				      .map(account -> Account.builder()
				    		  .balance(account.balance)
				    		  .build()
					  ).collect(Collectors.toList())
				   )
				   .accountHistories(customerEntity.accountHistoryEntityies.stream()
					   .map(history -> AccountHistory.builder()
							  .amount(history.amount)
							  .createTime(history.createTime)
							  .orderId(history.orderId).build())
					   .collect(Collectors.toList())
					)
				   .build();
	}

}
