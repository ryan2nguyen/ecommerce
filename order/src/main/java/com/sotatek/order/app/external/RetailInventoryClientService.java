package com.sotatek.order.app.external;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sotatek.order.app.external.dto.AccountDto;
import com.sotatek.order.app.external.dto.ProductDto;
import com.sotatek.order.ws.dto.ResponseDataDto;

import feign.Headers;
import feign.RequestLine;

public interface RetailInventoryClientService {

	
	@RequestLine("POST /product/fetch-price-by-id")
    @Headers("Content-Type: application/json")
	public ResponseDataDto<?> fetchPriceById(List<ProductDto> productDtos);
	
	@RequestLine("POST /product/deduct-inventory")
    @Headers("Content-Type: application/json")
	public ResponseDataDto<?> deductInventory(List<ProductDto> productDtos);

}
