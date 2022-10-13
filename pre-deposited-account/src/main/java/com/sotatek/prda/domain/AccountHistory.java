package com.sotatek.prda.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountHistory {
    
    public Long id;

    public String type;
    
    public Date createTime;
    
    public Long amount;
    
    public Long orderId;
    
    public Customer customer;
}
