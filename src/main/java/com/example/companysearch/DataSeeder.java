package com.example.companysearch;

import com.example.companysearch.model.Company;
import com.example.companysearch.repository.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import java.util.UUID;

@Component
@Slf4j
public class DataSeeder implements CommandLineRunner {

	@Autowired
	private CompanyRepository repository;

	@Override
	public void run(String... args) throws Exception {
		log.info("starting data seeding...");

		Company motorola = Company.builder()
			        .id(UUID.randomUUID().toString())
				.Name("Motorola").State("IL")
                                .Country("USA")
				.Email("epasham@motorola.com").build();

		Company cisco = Company.builder()
			        .id(UUID.randomUUID().toString())
				.Name("Cisco").State("CA")
                                .Country("USA")
				.Email("epasham@cisco.com").build();

		repository.deleteAll().thenMany(
				Flux.just(motorola, cisco)
				.flatMap(c -> repository.save(c))
		).subscribe( null,
				throwable ->  log.error("Could not save company", throwable ),
		() -> log.info("Finished data seeder..."));

		repository.count().subscribe(c -> log.info("Inserted " + c + " companies!"));


	}
}
