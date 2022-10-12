package com.sotatek.rea.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sotatek.rea.app.external.OrderClientService;
import com.sotatek.rea.app.external.RetailInventoryClientService;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

@Configuration
public class FeignClientConfig {

	@Autowired
	private AppRunningProperties appRunningProperties;
	
	@Bean
    public OrderClientService orderClientService() {
        return Feign.builder()
	    		  .client(new OkHttpClient())
	    		  .encoder(new GsonEncoder())
	    		  .decoder(new GsonDecoder())
	    		  .logger(new Slf4jLogger(OrderClientService.class))
	    		  .logLevel(Logger.Level.FULL)
	    		  .target(OrderClientService.class, this.appRunningProperties.getBaseUrl()+"/order");
    }
	
	@Bean
    public RetailInventoryClientService retailInventoryClientService() {
        return Feign.builder()
	    		  .client(new OkHttpClient())
	    		  .encoder(new GsonEncoder())
	    		  .decoder(new GsonDecoder())
	    		  .logger(new Slf4jLogger(RetailInventoryClientService.class))
	    		  .logLevel(Logger.Level.FULL)
	    		  .target(RetailInventoryClientService.class, this.appRunningProperties.getBaseUrl()+"/retail-inventory");
    }
	
}
