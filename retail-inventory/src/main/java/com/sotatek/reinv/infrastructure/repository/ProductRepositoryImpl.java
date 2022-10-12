package com.sotatek.reinv.infrastructure.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.reinv.domain.Product;
import com.sotatek.reinv.infrastructure.entity.ProductEntity;
import com.sotatek.reinv.infrastructure.jpa.JpaProductRepository;
import com.sotatek.reinv.ws.dto.SettlementDto;

@Service
public class ProductRepositoryImpl implements ProductReposity{

	@Autowired
	private JpaProductRepository jpaProductRepository;
	
	@Override
	public List<SettlementDto> findByTypeAndCreateTime(String type, String createTime) {
		return jpaProductRepository.findByTypeAndCreateTime(type, createTime);
	}

	@Override
	public Product findById(Long id) {
		// TODO Auto-generated method stub
		return toDomain(jpaProductRepository.findById(id).get());
	}

	@Override
	public Product save(Product prodcut) {
		// TODO Auto-generated method stub
		return toDomain(jpaProductRepository.save(ProductEntity.builder()
				.description(prodcut.description)
				.name(prodcut.name)
				.price(prodcut.price)
				.quantity(prodcut.quantity)
				.retailId(prodcut.retailId)
				.build()));
	}
	
	private Product toDomain(ProductEntity productEntity) {
		return Product.builder()
				.description(productEntity.description)
				.id(productEntity.id)
				.name(productEntity.name)
				.price(productEntity.price)
				.quantity(productEntity.quantity)
				.retailId(productEntity.retailId)
				.build();
	}

}
