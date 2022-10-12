package com.sotatek.order.infrastructure.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sotatek.order.domain.Order;
import com.sotatek.order.domain.OrderDetail;
import com.sotatek.order.infrastructure.entity.OrderDetailEntity;
import com.sotatek.order.infrastructure.entity.OrderEntity;
import com.sotatek.order.infrastructure.jpa.JpaOrderDetailRepository;
import com.sotatek.order.infrastructure.jpa.JpaOrderRepository;
import com.sotatek.order.ws.dto.SettlementDto;

@Component
public class OrderRepositoryImpl implements OrderRepository{

	@Autowired
	private JpaOrderRepository jpaOrderRepository;
	
	@Autowired
	private JpaOrderDetailRepository jpaOrderDetailRepository;
	
	@Override
	public Order findById(Long id) {
		return toDomain(jpaOrderRepository.findById(id).get());
	}

	@Override
	public Order save(Order order) {
		OrderEntity orderEntity = jpaOrderRepository.save(
			OrderEntity.builder()
			   .id(order.id)
		       .totalAmount(order.totalAmount)
			   .customerId(order.customerId)
			   .createTime(new Date())
			   .state(order.state)
			   .build()
		);
		
		List<OrderDetailEntity> orderDetailEntities = order.orderDetails.stream()
				      .map(detail -> OrderDetailEntity.builder()
				          .price(detail.price)
				          .productId(detail.productId)
				          .quantity(detail.quantity)
				          .build()
					  ).collect(Collectors.toList());
		List<OrderDetailEntity> savedDetail = jpaOrderDetailRepository.saveAll(orderDetailEntities);
		orderEntity.orderDetails = savedDetail;
		return toDomain(orderEntity);
	}
	
	private Order toDomain(OrderEntity orderEntity) {
		return Order.builder()
			       .customerId(orderEntity.customerId)
				   .state(orderEntity.state)
				   .totalAmount(orderEntity.totalAmount)
				   .createTime(orderEntity.createTime)
				   .orderDetails(orderEntity.orderDetails.stream()
				      .map(detail -> OrderDetail.builder()
				          .price(detail.getPrice())
				          .productId(detail.productId)
				          .quantity(detail.quantity)
				          .build()
					  ).collect(Collectors.toList())
				   )
				   .build();
	}

	@Override
	public List<SettlementDto> findByStateAndCreateTime(String state, String createTime) {
		// TODO Auto-generated method stub
		return jpaOrderRepository.findByStateAndCreateTime(state, createTime);
	}
}
