package com.sotatek.rea.app;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.rea.domain.Retail;
import com.sotatek.rea.infrastructure.repository.RetailRepository;
import com.sotatek.rea.ws.dto.ResponseDataDto;
import com.sotatek.rea.ws.dto.RetailDto;

import kong.unirest.HttpStatus;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RetailServiceImpl implements RetailService{
	@Autowired
	private RetailRepository retailRepository;
	
	@Override
	public ResponseDataDto<?> add(List<RetailDto> retails) {
		try {
			for(RetailDto retail: retails) {
				this.add(retail);
			}
			return new ResponseDataDto<String>(HttpStatus.OK, "Done");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	private ResponseDataDto<?> add(RetailDto retail) {
		try {
			if(retail.token.isBlank()) {
				retail.token = org.apache.commons.codec.digest.DigestUtils.sha256Hex(retail.toString() + UUID.randomUUID().toString());
			}
			Retail savedRetail = retailRepository.save(
					Retail.builder()
					.email(retail.email)
					.phone(retail.phone)
					.name(retail.name)
					.token(retail.token)
					.build()
			);
			return new ResponseDataDto<Retail>(HttpStatus.OK, savedRetail);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
