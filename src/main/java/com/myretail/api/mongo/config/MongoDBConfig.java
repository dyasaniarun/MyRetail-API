package com.myretail.api.mongo.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.myretail.api.repository.ProductRepository;
import com.myretail.api.v1.model.Product;



@EnableMongoRepositories(basePackageClasses = ProductRepository.class)
@Configuration
public class MongoDBConfig {
	
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository ProductRepository) {
        return strings -> {
        	ProductRepository.save(new Product(13860428, "", 48.99));
        	ProductRepository.save(new Product(13860424, "", 51.43));
        	ProductRepository.save(new Product(16696652, "", 96.17));
        	ProductRepository.save(new Product(13860427, "", 28.62));
        };
    }

}
