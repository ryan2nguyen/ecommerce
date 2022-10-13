package com.sotatek.rea.domain;

import java.util.Date;

import javax.persistence.Column;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountHistory {
	
	public Long id;

	public Retail retail;
	
    public String type;
	
    public Date createTime;
	
    public Long amount;
	
    public Long orderId;
}
