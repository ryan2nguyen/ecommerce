package com.sotatek.rea.app;

import java.util.List;

import com.sotatek.rea.domain.Settlement;
import com.sotatek.rea.ws.dto.ResponseDataDto;
import com.sotatek.rea.ws.dto.SettlementDto;

public interface SettlementService {
    
    public List<Settlement>  jobHandle() throws Exception;
}
