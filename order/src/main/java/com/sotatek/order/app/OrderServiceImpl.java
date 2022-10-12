package com.sotatek.order.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.order.app.external.AccountCustomerService;
import com.sotatek.order.app.external.AccountRetailService;
import com.sotatek.order.app.external.ProductService;
import com.sotatek.order.app.external.dto.ProductDto;
import com.sotatek.order.domain.Order;
import com.sotatek.order.domain.OrderDetail;
import com.sotatek.order.infrastructure.repository.OrderRepository;
import com.sotatek.order.ws.dto.OrderDetailDto;
import com.sotatek.order.ws.dto.OrderDto;
import com.sotatek.order.ws.dto.ResponseDataDto;
import com.sotatek.order.ws.dto.SettlementDto;

import kong.unirest.HttpStatus;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AccountCustomerService accountCustomerService;
	
	@Autowired
	private AccountRetailService accountRetailService;
	
	@Override
	public ResponseDataDto<?> callbackState(OrderDto orderDto) {
		try {
			if(orderDto.orderId == null || orderDto.orderId <= 0) {
				throw new Exception("orderId is in wrong format");
			}
			if(orderDto.state.isBlank()) {
				throw new Exception("state is in wrong format");
			}
			Order order = orderRepository.findById(orderDto.orderId);
			order.state = orderDto.state;
			order.createTime = new Date();
			orderRepository.save(order);
			return new ResponseDataDto<Order>(HttpStatus.OK, order);
		} catch (Exception e) {
			return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@Override
	public ResponseDataDto<?> findForSettlement() {
		// TODO Auto-generated method stub
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
//			calendar.add(Calendar.DATE, -1);
			Date yesterday = calendar.getTime();
			return new ResponseDataDto<List<SettlementDto>>(HttpStatus.OK, orderRepository.findByStateAndCreateTime("sold", dateFormat.format(yesterday)));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@Override
	public ResponseDataDto<?> createOrder(List<OrderDetailDto> orderDetails, Long customerId) {
		try {
			if(orderDetails.size() == 0) {
				throw new Exception("Product list is empty");
			}
			if(customerId == null || customerId <= 0) {
				throw new Exception("Customer doesn't exist");
			}
			
			Long totalAmount = 0L;
			List<ProductDto> productDtos = productService.fetchPriceById(orderDetails);
			for (OrderDetailDto orderDetail : orderDetails) {
                orderDetail.price = productDtos.stream().filter(product -> product.id == orderDetail.productId).findFirst().get().price;
				
                totalAmount += orderDetail.price * orderDetail.quantity;
			}
			
			Order savedOrder = orderRepository.save(Order.builder()
					.customerId(customerId)
					.createTime(new Date())
					.totalAmount(totalAmount)
					.state("pending")
					.orderDetails(orderDetails.stream().map(t -> OrderDetail.builder()
								.price(t.price)
								.productId(t.productId)
								.quantity(t.quantity)
								.build()
							).collect(Collectors.toList()))
					.build());
			
			accountCustomerService.payOrder(customerId, totalAmount);
			
			// update state
			savedOrder.state = "sold";
			orderRepository.save(savedOrder);
			
			// receive amount
			accountRetailService.receiveAmount(productDtos, totalAmount);
			
			// deducted inventory
			productService.deductInventory(productDtos);
			
			return new ResponseDataDto<Order>(HttpStatus.OK, savedOrder);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
