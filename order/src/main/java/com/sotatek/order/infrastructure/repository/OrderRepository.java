package com.sotatek.order.infrastructure.repository;

import java.util.List;

import com.sotatek.order.domain.Order;
import com.sotatek.order.ws.dto.SettlementDto;

public interface OrderRepository {

    public Order findById(Long id);
    
    public Order save(Order order);
    
    public List<SettlementDto> findByStateAndCreateTime(String state, String createTime);
}
