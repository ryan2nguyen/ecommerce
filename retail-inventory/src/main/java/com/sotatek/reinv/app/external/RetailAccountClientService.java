package com.sotatek.reinv.app.external;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sotatek.reinv.ws.dto.ResponseDataDto;

import feign.Param;
import feign.RequestLine;

@FeignClient(name = "retail-account")
public interface RetailAccountClientService {

    @GetMapping(value = "/retail/find-by-retail-id")
    public ResponseDataDto<?> findByRetailId(@RequestParam("retailId") Long retailId);
    
}
