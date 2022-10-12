package com.sotatek.order.app.external;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.order.app.external.dto.ProductDto;
import com.sotatek.order.config.AppRunningProperties;
import com.sotatek.order.config.util.GatewayConst;
import com.sotatek.order.ws.dto.OrderDetailDto;
import com.sotatek.order.ws.dto.ResponseDataDto;

import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private AppRunningProperties appRunningProperties;
	
	@Override
	public List<ProductDto> fetchPriceById(List<OrderDetailDto> orderDetails) throws Exception {
		try {
			ResponseDataDto orderResponse = Unirest.post(appRunningProperties.getBaseUrl() + "/retail-inventory/product/fetch-price-by-id")
				      .header("Content-Type", "application/json")
				      .header("Authorization", "Bearer " + GatewayConst.TOKEN_ADMIN)
				      .body(orderDetails)
				      .asObject(ResponseDataDto.class)
				      .getBody();
			if(orderResponse.code != 200) {
				throw new Exception(orderResponse.data.toString());
			}
			List<ProductDto> object = (List<ProductDto>) orderResponse.data;
			return object;
		} catch (Exception e) {
			throw new Exception("Error calling order/create-order: " + e.getMessage());
		}
	}

	@Override
	public List<ProductDto> deductInventory(List<ProductDto> productDtos) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
