package com.sotatek.prda.infrastructure.repository;

import java.util.List;
import java.util.stream.Stream;

import com.sotatek.prda.domain.Customer;

public interface CustomerRepository {

    public Customer findById(Long customerId);
    
    public Customer save(Customer customer);
    
    public List<Customer> getAll();
}
