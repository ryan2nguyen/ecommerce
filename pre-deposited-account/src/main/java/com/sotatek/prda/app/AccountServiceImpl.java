package com.sotatek.prda.app;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.prda.domain.Account;
import com.sotatek.prda.domain.AccountHistory;
import com.sotatek.prda.domain.Customer;
import com.sotatek.prda.infrastructure.repository.AccountHistoryRepository;
import com.sotatek.prda.infrastructure.repository.AccountRepository;
import com.sotatek.prda.infrastructure.repository.CustomerRepository;
import com.sotatek.prda.ws.dto.OrderDto;
import com.sotatek.prda.ws.dto.ResponseDataDto;

import kong.unirest.HttpStatus;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountHistoryRepository accountHistoryRepository;
	
	@Override
	public ResponseDataDto<?> payOrder(OrderDto orderDto, Long customerId) {
		try {
			if(orderDto.totalAmount == null) {
				throw new Exception("totalAmount doesn't exist");
			}
			if(customerId == null || customerId <= 0) {
				throw new Exception("customerId doesn't exist");
			}
			if(orderDto.orderId == null || orderDto.orderId <= 0) {
				throw new Exception("orderId doesn't exist");
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
			log.info("totalAmount: {}", orderDto.totalAmount);
			if(account.balance == null) {
				account.balance = 0L;
			}
			if(orderDto.totalAmount > account.balance) {
				log.error("Insufficient balance");
				throw new Exception("Customer "+ customerId + " insufficient balance");
			}
			account.balance = account.balance - orderDto.totalAmount;
			accountRepository.save(account);
			

			AccountHistory savedHistory = accountHistoryRepository.save(
					AccountHistory.builder()
					.amount(orderDto.totalAmount)
					.createTime(new Date())
					.customer(customer)
					.type("buy")
					.orderId(orderDto.orderId)
					.build()
			);
			return new ResponseDataDto<AccountHistory>(HttpStatus.OK, savedHistory);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
