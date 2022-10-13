package com.sotatek.order.app.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sotatek.order.app.external.dto.AccountDto;
import com.sotatek.order.app.external.dto.ProductDto;
import com.sotatek.order.ws.dto.ResponseDataDto;

import feign.Headers;
import feign.RequestLine;

@FeignClient(name = "retail-inventory")
public interface RetailInventoryClientService {

	
	@PostMapping(value = "/product/fetch-price-by-id")
    @Headers("Content-Type: application/json")
	public ResponseDataDto<?> fetchPriceById(@RequestBody List<ProductDto> productDtos);
	
	@PostMapping(value = "/product/deduct-inventory")
    @Headers("Content-Type: application/json")
	public ResponseDataDto<?> deductInventory(@RequestBody List<ProductDto> productDtos);

}
