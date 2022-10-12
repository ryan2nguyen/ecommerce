package com.sotatek.rea.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sotatek.rea.infrastructure.entity.AccountHistoryEntity;

@Repository
public interface JpaAccountHistoryRepository extends JpaRepository<AccountHistoryEntity, Long> {
	
}
