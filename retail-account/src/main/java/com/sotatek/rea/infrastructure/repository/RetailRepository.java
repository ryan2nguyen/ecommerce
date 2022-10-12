package com.sotatek.rea.infrastructure.repository;

import java.util.stream.Stream;

import com.sotatek.rea.domain.Retail;

public interface RetailRepository {

	public Stream<Retail> getAll();
	
    public Retail findByToken(String token);
	
	public Retail save(Retail retail);
	
	public Retail findById(Long retailId);
}
