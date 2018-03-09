package com.example.companysearch.config;

import com.example.companysearch.handler.CompanyHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouteConfig {
	
	@Bean
	RouterFunction<ServerResponse> routes(CompanyHandler handler) {
		return route(GET("/company"), request -> handler.getAll(request));

	}
	
}
