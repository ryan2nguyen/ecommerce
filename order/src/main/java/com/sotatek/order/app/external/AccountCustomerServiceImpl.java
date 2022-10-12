package com.sotatek.order.app.external;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.order.app.external.dto.AccountCustomerDto;
import com.sotatek.order.config.AppRunningProperties;
import com.sotatek.order.config.util.GatewayConst;
import com.sotatek.order.ws.dto.OrderDto;
import com.sotatek.order.ws.dto.ResponseDataDto;

import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AccountCustomerServiceImpl implements AccountCustomerService {
	
	@Autowired
	private AppRunningProperties appRunningProperties;

	public Map<String, Object> payOrder(Long customerId, Long totalAmount) throws Exception{
		try {
			ResponseDataDto response = Unirest.post(appRunningProperties.getBaseUrl() + "/pre-deposited-account/pay-order")
				      .header("Content-Type", "application/json")
				      .header("userId", customerId.toString())
				      .header("Authorization", "Bearer " + GatewayConst.TOKEN_ADMIN)
				      .body(new AccountCustomerDto(customerId, totalAmount))
				      .asObject(ResponseDataDto.class)
				      .getBody();
			if(response.code != 200) {
				throw new Exception(response.data.toString());
			}
			Map<String, Object> object = (Map<String, Object>) response.data;
			return object;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception("Error calling pre-deposited-account/pay-order: " + e.getMessage());
		}
	}
}
