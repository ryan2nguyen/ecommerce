package com.sotatek.rea.app.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sotatek.rea.ws.dto.ResponseDataDto;

import feign.Headers;
import feign.RequestLine;

@FeignClient(name = "order")
public interface OrderClientService {

    @GetMapping(value = "/find-for-settlement")
    public ResponseDataDto<?> findForSettlement();
}
