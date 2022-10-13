package com.sotatek.rea.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	
	public Long id;

	public Long balance;
    
    public Retail retail;
}
