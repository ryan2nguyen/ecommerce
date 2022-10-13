//package com.sotatek.reinv.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.sotatek.reinv.app.external.RetailAccountClientService;
//
//import feign.Feign;
//import feign.Logger;
//import feign.gson.GsonDecoder;
//import feign.gson.GsonEncoder;
//import feign.okhttp.OkHttpClient;
//import feign.slf4j.Slf4jLogger;
//
//@Configuration
//public class FeignClientConfig {
//
//	@Autowired
//	private AppRunningProperties appRunningProperties;
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
//}
