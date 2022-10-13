package com.sotatek.rea.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Retail {
    
    public Long id;

    public String name;
    
    public String email;
    
    public String phone;
    
    public String token;
    
    public List<Account> accounts;
    
    public List<AccountHistory> accountHistories;
}
