package com.sotatek.reinv.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.reinv.domain.Product;
import com.sotatek.reinv.domain.ProductHistory;
import com.sotatek.reinv.infrastructure.entity.ProductEntity;
import com.sotatek.reinv.infrastructure.entity.ProductHistoryEntity;
import com.sotatek.reinv.infrastructure.jpa.JpaProductHistoryRepository;
import com.sotatek.reinv.infrastructure.jpa.JpaProductRepository;

@Service
public class ProductHistoryRepositoryImpl implements ProductHistoryReposity {

	@Autowired
	private JpaProductHistoryRepository jpaProductHistoryRepository;
	
	@Autowired
	private JpaProductRepository jpaProductRepository;
	
	@Override
	public ProductHistory save(ProductHistory productHistory) {
		// TODO Auto-generated method stub
		return toDomain(jpaProductHistoryRepository.save(
				ProductHistoryEntity.builder()
				.createTime(productHistory.createTime)
				.orderId(productHistory.orderId)
				.quantity(productHistory.quantity)
				.type(productHistory.type)
				.product(ProductEntity.builder()
						.id(productHistory.product.id)
						.build()
				).build()
		));
	}

	private ProductHistory toDomain(ProductHistoryEntity productHistoryEntity) {
		return ProductHistory.builder()
				.id(productHistoryEntity.id)
				.createTime(productHistoryEntity.createTime)
				.orderId(productHistoryEntity.orderId)
				.quantity(productHistoryEntity.quantity)
				.type(productHistoryEntity.type)
				.product(Product.builder()
						.description(productHistoryEntity.product.description)
						.id(productHistoryEntity.product.id)
						.name(productHistoryEntity.product.name)
						.price(productHistoryEntity.product.price)
						.quantity(productHistoryEntity.product.quantity)
						.retailId(productHistoryEntity.product.retailId)
						.build()
				)
				.build();
	}
}
