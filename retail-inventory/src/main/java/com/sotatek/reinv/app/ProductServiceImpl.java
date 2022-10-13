package com.sotatek.reinv.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sotatek.reinv.app.external.RetailAccountClientService;
import com.sotatek.reinv.domain.Product;
import com.sotatek.reinv.domain.ProductHistory;
import com.sotatek.reinv.infrastructure.repository.ProductHistoryReposity;
import com.sotatek.reinv.infrastructure.repository.ProductReposity;
import com.sotatek.reinv.ws.dto.ProductDto;
import com.sotatek.reinv.ws.dto.ResponseDataDto;
import com.sotatek.reinv.ws.dto.SettlementDto;

import kong.unirest.HttpStatus;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    
    @Autowired
    private ProductReposity productReposity;
    
    @Autowired
    private ProductHistoryReposity productHistoryReposity;
    
    @Autowired
    private RetailAccountClientService retailAccountClientService;
    
    @Override
    public ResponseDataDto<?> add(List<ProductDto> productDtos) {
        try {
            productDtos.forEach(product -> this.add(product));
            return new ResponseDataDto<String>(HttpStatus.OK, "Done");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    private ResponseDataDto<?> add(ProductDto productDto) {
        try {
            ResponseDataDto response = retailAccountClientService.findByRetailId(productDto.retailId);
            if(response.code != 200) {
                throw new Exception(response.data.toString());
            }
            return new ResponseDataDto<Product>(HttpStatus.OK, productReposity.save(Product.builder()
                    .description(productDto.description)
                    .name(productDto.name)
                    .price(productDto.price)
                    .quantity(productDto.quantity)
                    .retailId(productDto.retailId)
                    .build()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseDataDto<?> increateInventory(ProductDto productDto) {
        try {
            Product savedProduct = productReposity.findById(productDto.productId);
            savedProduct.quantity += productDto.quantity;
            if(savedProduct.quantity >= 0) {
                productReposity.save(savedProduct);
                
                productHistoryReposity.save(ProductHistory.builder()
                        .createTime(new Date())
                        .quantity(savedProduct.quantity)
                        .type("increase")
                        .product(savedProduct)
                        .build());
            }
            return new ResponseDataDto<String>(HttpStatus.OK, "Done");
        } catch (Exception e) {
            return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseDataDto<?> fetchPriceById(List<ProductDto> productDtos) {
        
        return new ResponseDataDto<List<Product>>(HttpStatus.OK, 
                productDtos.stream().map(productDto -> productReposity.findById(productDto.productId)).collect(Collectors.toList())
        );
    }

    @Override
    public ResponseDataDto<?> deductInventory(List<ProductDto> productDtos) {
        try {
            productDtos.forEach(product -> {
                Product savedProduct = productReposity.findById(product.productId);
                savedProduct.quantity -= product.quantity;
                if(savedProduct.quantity >= 0) {
                    productReposity.save(savedProduct);
                    
                    productHistoryReposity.save(ProductHistory.builder()
                            .createTime(new Date())
                            .quantity(product.quantity)
                            .type("buy")
                            .product(savedProduct)
                            .build());
                }
            });
            return new ResponseDataDto<String>(HttpStatus.OK, "Done");
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
//            calendar.add(Calendar.DATE, -1);
            Date yesterday = calendar.getTime();
            return new ResponseDataDto<List<SettlementDto>>(HttpStatus.OK, productReposity.findByTypeAndCreateTime("buy", dateFormat.format(yesterday)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseDataDto<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
