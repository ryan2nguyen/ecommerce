package com.sotatek.reinv.infrastructure.repository;

import java.util.List;

import com.sotatek.reinv.domain.Product;
import com.sotatek.reinv.ws.dto.SettlementDto;

public interface ProductReposity {

	public List<SettlementDto> findByTypeAndCreateTime(String type, String createTime);
	
	public Product findById(Long id);

	public Product save(Product prodcut);
}
