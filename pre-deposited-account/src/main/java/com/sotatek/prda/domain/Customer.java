package com.sotatek.prda.domain;

import java.util.List;

import lombok.Builder;

@Builder
public class Customer {
	
	public Long id;

    public String name;
    
    public String email;
    
    public String phone;
    
    public String token;
    
    public List<Account> accounts;
    
    public List<AccountHistory> accountHistories;
}
