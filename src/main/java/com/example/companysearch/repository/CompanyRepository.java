package com.example.companysearch.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;


public interface CompanyRepository extends ReactiveCrudRepository<Company, String> {

  Mono<Company> findByName(String name);
  
  Flux<Company> findCompanyByCountry(String country);
  
  @Query("{'state' : ?0, 'country' : ?1}")
	Flux<Company> findByStateAndCountry(String state, String country);
  
}
