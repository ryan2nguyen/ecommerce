package com.sotatek.reinv.infrastructure.repository;

import com.sotatek.reinv.domain.ProductHistory;

public interface ProductHistoryReposity {
    
    ProductHistory save(ProductHistory productHistory);

}
