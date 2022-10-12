package com.sotatek.rea.infrastructure.jpa;

import java.util.stream.Stream;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sotatek.rea.infrastructure.entity.RetailEntity;

@Repository
public interface JpaRetailRepository extends JpaRepository<RetailEntity, Long> {

	@QueryHints(value = {
            @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE),
            @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "false"),
            @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_READONLY, value = "true")
    })
    @Query("SELECT r FROM retail r")
    Stream<RetailEntity> getAll();
	
	RetailEntity findByToken(@Param("token") String token);
}
