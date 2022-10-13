package com.sotatek.rea.infrastructure.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sotatek.rea.domain.Settlement;
import com.sotatek.rea.infrastructure.entity.SettlementEntity;
import com.sotatek.rea.infrastructure.jpa.JpaSettlementRepository;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class SettlementRepositoryImpl implements SettlementRepository{
    
    @Autowired
    private JpaSettlementRepository jpaSettlementRepository;
    
    @Override
    public Settlement save(Settlement settlement) {
        // TODO Auto-generated method stub
        return toDomain(jpaSettlementRepository.save(
                SettlementEntity.builder()
                .createTime(new Date())
                .retailId(settlement.retailId)
                .state(settlement.state)
                .orderAmount(settlement.orderAmount)
                .retailAmount(settlement.retailAmount)
                .build()
                ));
    }
    
    private Settlement toDomain(SettlementEntity entity) {
        if(entity == null) return null;
        return Settlement.builder()
                   .createTime(entity.createTime)
                   .retailId(entity.retailId)
                   .state(entity.state)
                   .orderAmount(entity.orderAmount)
                   .retailAmount(entity.retailAmount)
                   .build();
    }

}
