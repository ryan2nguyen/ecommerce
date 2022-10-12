package com.sotatek.order.domain.createorder;

import java.util.Date;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sotatek.order.infrastructure.model.Order;
import com.sotatek.order.infrastructure.model.OrderDetail;
import com.sotatek.order.infrastructure.repository.OrderDetailRepository;
import com.sotatek.order.infrastructure.repository.OrderRepository;
import com.sotatek.order.infrastructure.util.GatewayConst;
import com.sotatek.order.infrastructure.util.ResponseData;

import kong.unirest.HttpResponse;
import kong.unirest.HttpStatus;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CreateOrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	public ResponseData<?> createOrder(List<CreateOrderReqDto> products, Long customerId) {
		try {
			/**
			 * 	public Long totalAmount;
			 *  public Long orderId;
			 */
			// deducted from customer’s pre-deposited account
			Long totalAmount = 0L;
			for (CreateOrderReqDto createOrderReqDto : products) {
				totalAmount += createOrderReqDto.price * createOrderReqDto.quantity;
			}
			Order order = new Order();
			order.customerId = customerId;
			order.createTime = new Date();
			order.totalAmount = totalAmount;
			order.state = "pending";
			orderRepository.save(order);
			products.stream().forEach(product -> {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.order = order;
				orderDetail.productId = product.productId;
				orderDetail.price = product.price;
				orderDetail.quantity = product.quantity;
				orderDetailRepository.save(orderDetail);
			});
			return new ResponseData<Order>(HttpStatus.OK, order);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseData<String>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
}
