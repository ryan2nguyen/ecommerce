package com.sotatek.order.app.external;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.order.app.external.dto.AccountRetailDto;
import com.sotatek.order.app.external.dto.ProductDto;
import com.sotatek.order.config.AppRunningProperties;
import com.sotatek.order.config.util.GatewayConst;
import com.sotatek.order.ws.dto.ResponseDataDto;

import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AccountRetailServiceImpl implements AccountRetailService{
	
	@Autowired
	private AppRunningProperties appRunningProperties;
	
	@Override
	public Object receiveAmount(List<ProductDto> productDtos, Long orderId) throws Exception {
		try {
			/**
			 * public Long retailId;
			 * public Long amount;
			 * public Long orderId;
			 */
			List<AccountRetailDto> accountRetailDtos = new ArrayList<>();
			
			Map<Object, Long> productSold = productDtos.stream()
					.collect(Collectors.groupingBy(product -> product.retailId, Collectors.summingLong(productT -> productT.price * productT.quantity)));
			
			List<Object> retailIds = new ArrayList<Object>(productSold.keySet());
			for (Object retailId : retailIds) {
				accountRetailDtos.add(
						AccountRetailDto.builder()
						.amount(productSold.get(retailId))
						.orderId(orderId)
						.retailId((Long)retailId)
						.build()
						);
			}
			
			
			
			ResponseDataDto response = Unirest.post(appRunningProperties.getBaseUrl() + "/account/receive-amount")
				      .header("Content-Type", "application/json")
				      .header("Authorization", "Bearer " + GatewayConst.TOKEN_ADMIN)
				      .body(accountRetailDtos)
				      .asObject(ResponseDataDto.class)
				      .getBody();
			
			if(response.code != 200) {
				throw new Exception(response.data.toString());
			}
			String object = response.data.toString();
			return object;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception("Error calling retail-account/receive-amount: " + e.getMessage());
		}
	}

}
