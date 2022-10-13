package com.sotatek.rea.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sotatek.rea.app.RetailService;
import com.sotatek.rea.ws.dto.ResponseDataDto;
import com.sotatek.rea.ws.dto.RetailDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("retail")
public class RetailController {

    @Autowired
    private RetailService retailService;
    
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseDataDto<?> retailAdd(@RequestBody List<RetailDto> retails) throws Exception {
        return retailService.add(retails);
    }
    
    @RequestMapping(value = "find-by-retail-id", method = RequestMethod.GET)
    public ResponseDataDto<?> findByRetailId(@RequestParam Long retailId) throws Exception {
        return retailService.findByRetailId(retailId);
    }
}
