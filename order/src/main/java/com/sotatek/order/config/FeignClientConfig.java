//package com.sotatek.order.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.sotatek.order.app.external.PreDepositedAccountClientService;
//import com.sotatek.order.app.external.RetailAccountClientService;
//import com.sotatek.order.app.external.RetailInventoryClientService;
//
//import feign.Feign;
//import feign.gson.GsonDecoder;
//import feign.gson.GsonEncoder;
//import feign.okhttp.OkHttpClient;
//import feign.slf4j.Slf4jLogger;
//import feign.Logger;
//
////@Configuration
//public class FeignClientConfig {
//
//	@Autowired
//	private AppRunningProperties appRunningProperties;
//	
//	@Bean
//    public PreDepositedAccountClientService preDepositedAccountClient() {
//        return Feign.builder()
//	    		  .client(new OkHttpClient())
//	    		  .encoder(new GsonEncoder())
//	    		  .decoder(new GsonDecoder())
//	    		  .logger(new Slf4jLogger(PreDepositedAccountClientService.class))
//	    		  .logLevel(Logger.Level.FULL)
//	    		  .target(PreDepositedAccountClientService.class, this.appRunningProperties.getBaseUrl()+"/"+appRunningProperties.getPreDepositedAccountHostname());
//    }
//	
//	@Bean
//    public RetailAccountClientService retailAccountClientService() {
//        return Feign.builder()
//	    		  .client(new OkHttpClient())
//	    		  .encoder(new GsonEncoder())
//	    		  .decoder(new GsonDecoder())
//	    		  .logger(new Slf4jLogger(RetailAccountClientService.class))
//	    		  .logLevel(Logger.Level.FULL)
//	    		  .target(RetailAccountClientService.class, this.appRunningProperties.getBaseUrl()+"/"+appRunningProperties.getRetailAccountHostname());
//    }
//	
//	@Bean
//    public RetailInventoryClientService retailInventoryClientService() {
//        return Feign.builder()
//	    		  .client(new OkHttpClient())
//	    		  .encoder(new GsonEncoder())
//	    		  .decoder(new GsonDecoder())
//	    		  .logger(new Slf4jLogger(RetailInventoryClientService.class))
//	    		  .logLevel(Logger.Level.FULL)
//	    		  .target(RetailInventoryClientService.class, this.appRunningProperties.getBaseUrl()+"/"+appRunningProperties.getRetailInventoryHostname());
//    }
//}
