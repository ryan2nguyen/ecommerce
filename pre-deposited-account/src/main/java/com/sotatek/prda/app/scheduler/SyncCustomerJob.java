package com.sotatek.prda.app.scheduler;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sotatek.prda.infrastructure.repository.CustomerRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class SyncCustomerJob {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
    private RedisTemplate<Object, Object> redisTemplate;

	@Scheduled(fixedRate = 5000)
    @Transactional(readOnly = true)
    public void running() {
		try {
			customerRepository.getAll().forEach(customer -> {
				redisTemplate.opsForValue().set(customer.token, User.builder().userId(customer.id).type("customer").build().toString(), 5, TimeUnit.MINUTES);
			});
		} catch (Exception e) {
			log.error("[SCHEDULE] " + e);
		}
	}
}
