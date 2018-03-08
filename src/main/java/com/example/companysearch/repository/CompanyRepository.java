package com.example.companysearch.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


public interface CompanyRepository extends ReactiveCrudRepository<Company, String> {

  Flux<Company> findByName(String name);
  
}
