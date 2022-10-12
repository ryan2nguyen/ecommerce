package com.sotatek.reinv.app.external;


import com.sotatek.reinv.ws.dto.ResponseDataDto;

import feign.Param;
import feign.RequestLine;

public interface RetailAccountClientService {

	@RequestLine("GET /retail/find-by-retail-id")
	public ResponseDataDto<?> findByRetailId(@Param("retailId-date") Long retailId);
	
}
