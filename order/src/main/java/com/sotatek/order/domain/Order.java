package com.sotatek.order.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    
    public Long id;

    public Long customerId;
    
    public Long totalAmount;
    
    public String state;
    
    public Date createTime;
    
    public List<OrderDetail> orderDetails;
}
