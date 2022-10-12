package com.sotatek.reinv.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sotatek.reinv.infrastructure.entity.ProductHistoryEntity;

@Repository
public interface JpaProductHistoryRepository extends JpaRepository<ProductHistoryEntity, Long> {
	
}
