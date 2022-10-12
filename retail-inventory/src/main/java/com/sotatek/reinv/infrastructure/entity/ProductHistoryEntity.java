package com.sotatek.reinv.infrastructure.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "product_history")
@Table(schema = "product_history")
public class ProductHistoryEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
	@ManyToOne
    @JoinColumn(name = "productId")
	public ProductEntity product;
	
	@Column(name = "type")
    public String type;
	
	@Column(name = "createTime")
    public Date createTime;
	
	@Column(name = "quantity")
    public Integer quantity;
	
	@Column(name = "orderId")
    public Long orderId;
}
