package com.example.companysearch.handler;

import com.example.companysearch.model.Company;
import com.example.companysearch.repository.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CompanyHandler {

	@Autowired
	private CompanyRepository repository;

  	public Mono<ServerResponse> getAll(ServerRequest request) {
		Flux<Company> company = repository.findAll();
		return ServerResponse.ok().body(BodyInserters.fromPublisher(company, Company.class));
	}



}
