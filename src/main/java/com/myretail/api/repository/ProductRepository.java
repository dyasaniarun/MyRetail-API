package com.myretail.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myretail.api.v1.model.Product;

public interface ProductRepository extends MongoRepository<Product, Long>{
	
	/**
	 * This method will find an Topic instance in the database by its product.
	 * Note that this method is not implemented and its working code will be 
	 * automatically generated from its signature by Spring DATA JPA.
	 */
	
}
