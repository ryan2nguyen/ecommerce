package com.sotatek.order.infrastructure.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sotatek.order.infrastructure.entity.OrderEntity;
import com.sotatek.order.ws.dto.SettlementDto;

@Repository
public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {
	
	@Query(
		value = "SELECT od.productId AS productId, SUM(od.quantity * od.price) AS amount, SUM(od.quantity) AS quantity FROM order_detail od WHERE od.orderId in (SELECT o.id FROM `order` o WHERE o.state = ?1 and DATE(o.createTime) = ?2) GROUP BY od.productId", 
		nativeQuery = true)
	List<SettlementDto> findByStateAndCreateTime(String state, String createTime);
}
