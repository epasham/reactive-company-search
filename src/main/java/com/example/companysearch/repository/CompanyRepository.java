package com.example.companysearch.repository;

import com.example.companysearch.model.Company;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.mongodb.repository.Query;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends ReactiveCrudRepository<Company, String> {

  Mono<Company> findByName(String name);
  
  Flux<Company> findCompanyByCountry(String country);
  
  @Query("{'state' : ?0, 'country' : ?1}")
  Flux<Company> findByStateAndCountry(String state, String country);
  
}
