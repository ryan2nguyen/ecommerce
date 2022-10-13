package com.sotatek.order.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.order.app.external.PreDepositedAccountClientService;
import com.sotatek.order.app.external.RetailAccountClientService;
import com.sotatek.order.app.external.RetailInventoryClientService;
import com.sotatek.order.app.external.dto.AccountDto;
import com.sotatek.order.app.external.dto.OrderDto;
import com.sotatek.order.app.external.dto.ProductDto;
import com.sotatek.order.domain.Order;
import com.sotatek.order.domain.OrderDetail;
import com.sotatek.order.infrastructure.repository.OrderRepository;
import com.sotatek.order.ws.dto.OrderDetailDto;
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
    private PreDepositedAccountClientService preDepositedAccountClientService;
    
    @Autowired
    private RetailAccountClientService retailAccountClientService;
    
    @Autowired
    private RetailInventoryClientService retailInventoryClientService;

    @Override
    public ResponseDataDto<?> findForSettlement() {
        // TODO Auto-generated method stub
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
//            calendar.add(Calendar.DATE, -1);
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
            ResponseDataDto<?> resultProducts = retailInventoryClientService.fetchPriceById(
                    orderDetails.stream().map(detail -> ProductDto.builder()
                            .productId(detail.productId)
                            .quantity(detail.quantity)
                            .build()).collect(Collectors.toList()));
            if(resultProducts.code != HttpStatus.OK) {
                throw new Exception(resultProducts.data.toString());
            }
            List<ProductDto> productDtos = ((List<Map<String, Object>>) resultProducts.data).stream().map(product -> 
                ProductDto.builder()
                .price(Long.parseLong(product.get("price").toString()))
                .productId(Long.parseLong(product.get("id").toString()))
                .retailId(Long.parseLong(product.get("retailId").toString()))
                .build()
            ).collect(Collectors.toList());
            
//            for (OrderDetailDto orderDetail : orderDetails) {
//                orderDetail.price = productDtos.stream().filter(product -> product.productId == orderDetail.productId).findFirst().get().price;
//                
//                totalAmount += orderDetail.price * orderDetail.quantity;                                                                                                                                                                            
//            }
            
            for (ProductDto productDto : productDtos) {
                productDto.quantity = orderDetails.stream().filter(order -> order.productId == productDto.productId).findFirst().get().quantity;
                
                totalAmount += productDto.price * productDto.quantity;                                                                                                                                                                            
            }
            
            Order savedOrder = orderRepository.save(Order.builder()
                    .customerId(customerId)
                    .createTime(new Date())
                    .totalAmount(totalAmount)
                    .state("pending")
                    .orderDetails(productDtos.stream().map(t -> OrderDetail.builder()
                                .price(t.price)
                                .productId(t.productId)
                                .quantity(t.quantity)
                                .build()
                            ).collect(Collectors.toList()))
                    .build());
            // String userId, OrderDto orderDto
            preDepositedAccountClientService.payOrder(customerId.toString(), OrderDto.builder().orderId(savedOrder.id).totalAmount(totalAmount).build());
            
            // update state
            savedOrder.state = "sold";
            orderRepository.save(savedOrder);
            
            // receive amount
            List<AccountDto> accountDtos = new ArrayList<>();
            
            Map<Object, Long> productSold = productDtos.stream()
                    .collect(Collectors.groupingBy(product -> product.retailId, Collectors.summingLong(product -> product.price * product.quantity)));
            
            (new ArrayList<Object>(productSold.keySet())).forEach(retail -> {
                accountDtos.add(
                        AccountDto.builder()
                        .amount(productSold.get(retail))
                        .orderId(savedOrder.id)
                        .retailId((Long)retail)
                        .build()
                        );
            });
            retailAccountClientService.receiveAmount(accountDtos);
            
            // deducted inventory
            retailInventoryClientService.deductInventory(productDtos);
            
            return new ResponseDataDto<Order>(HttpStatus.OK, savedOrder);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
