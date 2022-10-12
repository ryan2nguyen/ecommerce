package com.sotatek.reinv.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.reinv.domain.Product;
import com.sotatek.reinv.infrastructure.repository.ProductReposity;
import com.sotatek.reinv.ws.dto.ProductDto;
import com.sotatek.reinv.ws.dto.ResponseDataDto;

import kong.unirest.HttpStatus;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductReposity productReposity;
	
	@Override
	public ResponseDataDto<?> add(List<ProductDto> productDtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDataDto<?> increateInventory(ProductDto productDto, Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDataDto<?> fetchPriceById(List<ProductDto> productDtos) {
		
		return new ResponseDataDto<List<Product>>(HttpStatus.OK, 
				productDtos.stream().map(productDto -> productReposity.findById(productDto.productId)).collect(Collectors.toList())
		);
	}

	@Override
	public ResponseDataDto<?> deductInventory(List<ProductDto> productDtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
