package com.sotatek.reinv.ws.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseDataDto<T> {

	public Integer code;
	
	public T data;
}
