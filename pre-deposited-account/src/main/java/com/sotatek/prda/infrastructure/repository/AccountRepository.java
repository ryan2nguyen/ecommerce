package com.sotatek.prda.infrastructure.repository;

import com.sotatek.prda.domain.Account;

public interface AccountRepository {

	public Account findByCustomerId(Long customerId);
	
	Account save(Account account);
}
