package com.sotatek.prda.app;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.prda.domain.Account;
import com.sotatek.prda.domain.AccountHistory;
import com.sotatek.prda.domain.Customer;
import com.sotatek.prda.infrastructure.repository.AccountHistoryRepository;
import com.sotatek.prda.infrastructure.repository.AccountRepository;
import com.sotatek.prda.infrastructure.repository.CustomerRepository;
import com.sotatek.prda.ws.dto.AccountDto;
import com.sotatek.prda.ws.dto.CustomerDto;
import com.sotatek.prda.ws.dto.ResponseDataDto;

import kong.unirest.HttpStatus;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountHistoryRepository accountHistoryRepository;
	
	@Override
	public ResponseDataDto<?> deposit(AccountDto accountDto, Long customerId) {
		try {
			if(accountDto.balance == null || accountDto.balance <= 0) {
				throw new Exception("balance doesn't exist");
			}
			if(customerId == null || customerId <= 0) {
				throw new Exception("customerId doesn't exist");
			}
			Customer customer = customerRepository.findById(customerId);
			if(customer == null) {
				throw new Exception("Customer "+ customerId +" doesn't exist");
			}
			Account account = accountRepository.findByCustomerId(customerId);
			if(account == null) {
				account = accountRepository.save(
						Account.builder()
						.balance(0L)
						.customer(customer)
						.build()
				);
			}
			if(account.balance == null) {
				account.balance = 0L;
			}
			account.balance = account.balance + accountDto.balance;
			accountRepository.save(account);
			
			AccountHistory savedHistory = accountHistoryRepository.save(
					AccountHistory.builder()
					.customer(account.customer)
					.type("deposit")
					.createTime(new Date())
					.amount(accountDto.balance)
					.build()
			);
			return new ResponseDataDto<AccountHistory>(HttpStatus.OK, savedHistory);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@Override
	public ResponseDataDto<?> add(List<CustomerDto> customers) {
		try {
			for(CustomerDto customer: customers) {
				this.add(customer);
			}
			return new ResponseDataDto<String>(HttpStatus.OK, "Done");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	private ResponseDataDto<?> add(CustomerDto customer) {
		try {
			if(customer.token.isBlank()) {
				customer.token = org.apache.commons.codec.digest.DigestUtils.sha256Hex(customer.toString() + UUID.randomUUID().toString());
			}
			Customer savedCustomer = customerRepository.save(
					Customer.builder()
					.email(customer.email)
					.phone(customer.phone)
					.name(customer.name)
					.token(customer.token)
					.build()
			);
			return new ResponseDataDto<Customer>(HttpStatus.OK, savedCustomer);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
