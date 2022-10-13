package com.sotatek.reinv.infrastructure.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sotatek.reinv.infrastructure.entity.ProductEntity;
import com.sotatek.reinv.ws.dto.SettlementDto;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    
    @Query(
        value = "SELECT ph.productId, SUM(ph.quantity) as quantity, p.retailId FROM product_history ph, product p WHERE ph.productId = p.id and ph.`type` = ?1 and DATE(ph.createTime)=?2 GROUP BY ph.productId", 
        nativeQuery = true)
    List<SettlementDto> findByTypeAndCreateTime(String type, String createTime);
}
