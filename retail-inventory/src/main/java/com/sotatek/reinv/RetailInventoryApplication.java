package com.sotatek.reinv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@SpringCloudApplication
@EnableFeignClients
public class RetailInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetailInventoryApplication.class, args);
    }

}
