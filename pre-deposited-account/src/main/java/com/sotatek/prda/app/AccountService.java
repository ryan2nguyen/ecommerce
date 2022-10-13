package com.sotatek.prda.app;

import com.sotatek.prda.ws.dto.OrderDto;
import com.sotatek.prda.ws.dto.ResponseDataDto;

public interface AccountService {

    public ResponseDataDto<?> payOrder(OrderDto orderDto, Long customerId);
    
}
