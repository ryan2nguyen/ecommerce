package com.sotatek.rea.app;

import java.util.List;

import com.sotatek.rea.ws.dto.ResponseDataDto;
import com.sotatek.rea.ws.dto.RetailDto;

public interface RetailService {

	public ResponseDataDto<?> add(List<RetailDto> retailDtos);
	
	public ResponseDataDto<?> findByRetailId(Long retailId);
}
