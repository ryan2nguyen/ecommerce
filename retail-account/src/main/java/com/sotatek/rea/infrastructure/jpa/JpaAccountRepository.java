package com.sotatek.rea.infrastructure.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sotatek.rea.infrastructure.entity.AccountEntity;
import com.sotatek.rea.ws.dto.SettlementDto;


@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {
	
	AccountEntity findByRetailId(@Param("retailId") Long retailId);
	
	@Query(
			value = "SELECT ah.retailId, SUM(ah.amount) as amount FROM account_history ah WHERE ah.`type` = ?1 and DATE(ah.createTime) = ?2 GROUP BY ah.retailId", 
			nativeQuery = true)
	List<SettlementDto> findByTypeAndCreateTime(String type, String createTime);
	
}
