package com.example.companysearch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
@EnableReactiveMongoRepositories
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    private final String mongoConnectionStringPrefix = "mongodb://";

    private String mongoDatabaseName;
    private String mongoUri;
    private String mongoPort;
    private String mongoUsername;
    private String mongoPassword;

    String connectionString;

    public MongoConfig(@Value("${spring.data.mongodb.database}") String mongoDatabaseName,
                       @Value("${spring.data.mongodb.host}") String mongoHost,
                       @Value("${spring.data.mongodb.port}") String mongoPort,
                       @Value("${spring.data.mongodb.username}") String mongoUsername,
                       @Value("${spring.data.mongodb.password}") String mongoPassword) {
        this.mongoDatabaseName = mongoDatabaseName;
        this.mongoHost = mongoHost;
        this.mongoPort = mongoPort;
        this.mongoUsername = mongoUsername;
        this.mongoPassword = mongoPassword;
    }

    @Bean
    @Qualifier("connectionString")
    public String buildConnectionString() {
        return mongoConnectionStringPrefix
                .concat(mongoUsername)
                .concat(":")
                .concat(mongoPassword)
                .concat("@")
                .concat(mongoHost)
                .concat(":")
                .concat(mongoPort)
                .concat("/")
                .concat(mongoDatabaseName);
    }

    @Autowired
    public void getConnectionString(@Qualifier("connectionString") String connectionString) {
        this.connectionString = connectionString;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(connectionString);
    }

    @Override
    protected String getDatabaseName() {
        return this.mongoDatabaseName;
    }

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(connectionString);
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient(), getDatabaseName());
    }
}
