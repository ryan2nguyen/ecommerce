package com.sotatek.rea.app.scheduler;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sotatek.rea.infrastructure.repository.RetailRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class SyncRetailJob {
    
    @Autowired
    private RetailRepository retailRepository;
    
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Scheduled(fixedRate = 5000)
    @Transactional(readOnly = true)
    public void running() {
        try {
            retailRepository.getAll().forEach(retail -> {
                redisTemplate.opsForValue().set(retail.token, User.builder().userId(retail.id).type("retail").build().toString(), 5, TimeUnit.MINUTES);
            });
        } catch (Exception e) {
            log.error("[SCHEDULE] " + e);
        }
    }
}
