package com.sotatek.reinv.infrastructure.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "product")
@Table(schema = "product")
public class ProductEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "quantity")
	public Integer quantity;
	
	@Column(name = "price")
	public Long price;
	
	@Column(name = "description")
	public String description;
	
	@Column(name = "retailId")
	public Long retailId;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<ProductEntity> productEntities;
	
}
