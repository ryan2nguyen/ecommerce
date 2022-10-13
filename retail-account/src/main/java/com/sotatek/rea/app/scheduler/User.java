package com.sotatek.rea.app.scheduler;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class User {
	public long userId;
	
	public String type = "retail";
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
