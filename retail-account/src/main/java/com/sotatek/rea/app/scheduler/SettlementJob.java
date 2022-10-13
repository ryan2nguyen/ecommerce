package com.sotatek.rea.app.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sotatek.rea.app.AccountService;
import com.sotatek.rea.app.SettlementService;
import com.sotatek.rea.domain.Settlement;
import com.sotatek.rea.infrastructure.repository.SettlementRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class SettlementJob {
    
    @Autowired
    private SettlementService settlementService;
    
    @Autowired
    private SettlementRepository settlementRepository;
    
    @Scheduled(cron = "0 0 1 * * ?")
//    @Scheduled(fixedRate = 5000)
    public void running() {
        try {
            List<Settlement> settlements = settlementService.jobHandle();
            for(Settlement settlement: settlements) {
                settlementRepository.save(settlement);
            }
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
