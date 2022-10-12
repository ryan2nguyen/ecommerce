package com.sotatek.order.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sotatek.order.app.OrderService;
import com.sotatek.order.ws.dto.OrderDetailDto;
import com.sotatek.order.ws.dto.OrderDto;
import com.sotatek.order.ws.dto.ResponseDataDto;

@RestController
@RequestMapping("")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "create-order", method = RequestMethod.POST)
    public ResponseDataDto<?> createOrder(@RequestHeader(value="userId") String userId, @RequestBody List<OrderDetailDto> products) throws Exception {
        return orderService.createOrder(products, Long.parseLong(userId));
    }
	
	@RequestMapping(value = "find-for-settlement", method = RequestMethod.GET)
    public ResponseDataDto<?> findForSettlement() throws Exception {
        return orderService.findForSettlement();
    }
	
	@RequestMapping(value = "callback-state", method = RequestMethod.POST)
    public ResponseDataDto<?> createOrder(@RequestBody OrderDto request) throws Exception {
        return orderService.callbackState(request);
    }
}
