package com.sotatek.order.app.external;

import java.util.List;

import com.sotatek.order.app.external.dto.ProductDto;
import com.sotatek.order.ws.dto.OrderDetailDto;
import com.sotatek.order.ws.dto.ResponseDataDto;


public interface ProductService {

	public List<ProductDto> fetchPriceById(List<OrderDetailDto> orderDetails) throws Exception;
	
	public List<ProductDto> deductInventory(List<ProductDto> productDtos) throws Exception;
}
