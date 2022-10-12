package com.sotatek.reinv.app;

import java.util.List;

import com.sotatek.reinv.ws.dto.ProductDto;
import com.sotatek.reinv.ws.dto.ResponseDataDto;


public interface ProductService {

	public ResponseDataDto<?> add(List<ProductDto> productDtos);
	
	public ResponseDataDto<?> increateInventory(ProductDto productDto);
	
	public ResponseDataDto<?> fetchPriceById(List<ProductDto> productDtos);
	
	public ResponseDataDto<?> deductInventory(List<ProductDto> productDtos);
	
	
}
