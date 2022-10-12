package com.sotatek.order.infrastructure.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sotatek.order.domain.OrderDetail;
import com.sotatek.order.infrastructure.entity.OrderDetailEntity;

@Repository
public interface JpaOrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
		
}
