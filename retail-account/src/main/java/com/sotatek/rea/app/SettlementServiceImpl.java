package com.sotatek.rea.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.rea.app.external.OrderClientService;
import com.sotatek.rea.app.external.RetailInventoryClientService;
import com.sotatek.rea.config.AppRunningProperties;
import com.sotatek.rea.config.util.GatewayConst;
import com.sotatek.rea.domain.Settlement;
import com.sotatek.rea.ws.dto.ResponseDataDto;
import com.sotatek.rea.ws.dto.SettlementDto;

import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SettlementServiceImpl implements SettlementService{
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private OrderClientService orderClientService;
	
	@Autowired
	private RetailInventoryClientService retailInventoryClientService;
	
	@Override
	public List<Settlement> jobHandle() throws Exception{
		try {
			ResponseDataDto orderResponse = orderClientService.findForSettlement();
			
			ResponseDataDto inventoryResponse = retailInventoryClientService.findForSettlement();
			
			ResponseDataDto retailAccountResponse = accountService.findForSettlement();
			
			if(orderResponse.code != 200) {
				throw new Exception(orderResponse.data.toString());
			}
			if(inventoryResponse.code != 200) {
				throw new Exception(inventoryResponse.data.toString());
			}
			if(retailAccountResponse.code != 200) {
				throw new Exception(retailAccountResponse.data.toString());
			}
			
			List<Map<String, Object>> orderRes = (List<Map<String, Object>>) orderResponse.data;  // amount
			
			List<Map<String, Object>> productRes = (List<Map<String, Object>>) inventoryResponse.data; // quantity
			
			List<SettlementDto> retails = (List<SettlementDto>) retailAccountResponse.data;
			
			orderRes.stream()
			.flatMap(order -> 
				productRes.stream()
				.filter(
					product -> product.get("productId").equals(order.get("productId"))
				)
				.map(
					product -> product.put("amount", order.get("amount"))
				)
			)
			.collect(Collectors.toList());
			
			List<Settlement> settlements = productRes.stream()
			.flatMap(product -> 
				retails.stream()
				.filter(
					retail -> Double.parseDouble(product.get("retailId").toString()) == retail.getRetailId()
				)
				.filter(
					retail -> Double.parseDouble(product.get("amount").toString()) == retail.getAmount()
				)
				.map(retail -> {
					if(Double.parseDouble(product.get("amount").toString()) == retail.getAmount()) {
						return Settlement.builder().createTime(new Date()).retailId(retail.getRetailId()).state("match").build();
					} else {
						return Settlement.builder().createTime(new Date()).retailId(retail.getRetailId()).state("unmatch").build();
					}
				})
					
			).collect(Collectors.toList());
			return settlements;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		}
	}

}
