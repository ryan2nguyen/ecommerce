package com.sotatek.rea.infrastructure.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sotatek.rea.domain.Account;
import com.sotatek.rea.ws.dto.SettlementDto;

public interface AccountRepository {
    
    List<SettlementDto> findByTypeAndCreateTime(String type, String createTime);
    
    Account findByRetailId(Long retailId);
    
    Account save(Account account);
    
}
