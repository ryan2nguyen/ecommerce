package com.sotatek.rea.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Settlement {

    public Date createTime;
	
	public Long retailId;
	
    public String state;
}
