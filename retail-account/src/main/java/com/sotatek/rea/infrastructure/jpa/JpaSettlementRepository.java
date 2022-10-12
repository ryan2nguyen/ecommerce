package com.sotatek.rea.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sotatek.rea.infrastructure.entity.SettlementEntity;

@Repository
public interface JpaSettlementRepository extends JpaRepository<SettlementEntity, Long> {

}
