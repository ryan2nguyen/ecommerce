package com.sotatek.prda.domain;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    
    public Long id;

    public Long balance;
    
    public Customer customer;
}
