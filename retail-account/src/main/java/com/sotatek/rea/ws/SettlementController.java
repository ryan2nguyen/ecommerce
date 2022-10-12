package com.sotatek.rea.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sotatek.rea.app.SettlementService;
import com.sotatek.rea.domain.Settlement;
import com.sotatek.rea.ws.dto.ResponseDataDto;

import kong.unirest.HttpStatus;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("settlement")
public class SettlementController {

	@Autowired
	private SettlementService settlementService;
	
	@RequestMapping(value = "manual-trigger", method = RequestMethod.GET)
    public ResponseDataDto<?> jobTrigger() throws Exception {
		try {
			return new ResponseDataDto<List<Settlement>>(HttpStatus.OK, settlementService.jobHandle());
		} catch (Exception e) {
			return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
        
    }
}
