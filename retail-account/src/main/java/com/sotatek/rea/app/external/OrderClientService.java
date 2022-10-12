package com.sotatek.rea.app.external;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sotatek.rea.ws.dto.ResponseDataDto;

import feign.Headers;
import feign.RequestLine;


public interface OrderClientService {

	@RequestLine("GET /find-for-settlement")
	public ResponseDataDto<?> findForSettlement();
}
