package com.sotatek.reinv.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sotatek.reinv.app.ProductService;
import com.sotatek.reinv.ws.dto.ProductDto;
import com.sotatek.reinv.ws.dto.ResponseDataDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseDataDto<?> manageProduct(@RequestBody List<ProductDto> productDtos) throws Exception{
		return productService.add(productDtos);
    }
	
	@RequestMapping(value = "fetch-price-by-id", method = RequestMethod.POST)
    public ResponseDataDto<?> fetchPriceById(@RequestBody List<ProductDto> productDtos) throws Exception {
        return productService.fetchPriceById(productDtos);
    }
	
	@RequestMapping(value = "deduct-inventory", method = RequestMethod.POST)
    public ResponseDataDto<?> deductInventory(@RequestBody List<ProductDto> productDtos) throws Exception {
        return productService.deductInventory(productDtos);
    }
	
	@RequestMapping(value = "increase-inventory", method = RequestMethod.POST)
    public ResponseDataDto<?> deductInventory(@RequestBody ProductDto productDto) throws Exception {
        return productService.increateInventory(productDto);
    }
}
