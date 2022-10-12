package com.sotatek.order.infrastructure.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "order_detail")
@Table(schema = "order_detail")
public class OrderDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
	@ManyToOne
    @JoinColumn(name = "orderId")
	public Order order;
	
	@Column(name = "price")
    public Long price;
	
	@Column(name = "productId")
    public Long productId;
	
	@Column(name = "quantity")
    public Integer quantity;
}
