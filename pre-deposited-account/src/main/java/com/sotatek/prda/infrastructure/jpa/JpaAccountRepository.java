package com.sotatek.prda.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sotatek.prda.infrastructure.entity.AccountEntity;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {
    
    AccountEntity findByCustomerId(@Param("customerId") Long customerId);
}
