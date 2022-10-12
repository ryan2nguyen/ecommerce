package com.sotatek.reinv.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	public Long id;

	public String name;
	
	public Integer quantity;
	
	public Long price;
	
	public String description;
	
	public Long retailId;
	
	public List<ProductHistory> productHistories;
	
}
