package com.myretail.api.v1.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.api.repository.ProductRepository;
import com.myretail.api.v1.exception.CustomMyRetailException;
import com.myretail.api.v1.model.Product;

//Product service is a singleton

@Service
public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Value("${hostname}")
	private String api_hostName;
	@Value("${apiParamPath}")
	private String apiParamPath = "/product/item/product_description/title";
	@Value("${apiPath}")
	private String apiPath = "v2/pdp/tcin/";
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	/*
	 * This method will retrieve the product id and name. It will return Product object.
	 */
	public Product retrieveProduct(Long productId) throws CustomMyRetailException {
		logger.info("***MR_METRICS*** - retrieveProduct method has been started at "+new Date().getTime());
		if(productId <= 0) 
			throw new CustomMyRetailException("Product ID is invalid.", HttpStatus.BAD_REQUEST);
		Product product =null;
		product = productRepository.findOne(productId);
		if(product == null) {
			logger.error("Product was not found for -"+productId);
			throw new CustomMyRetailException("Product - "+productId+" was not found.", HttpStatus.NOT_FOUND);						
		}
		String name = this.retrieveProductName(productId);
		if(name == null) {
			logger.error("Product Name was not found for product ID -"+productId);
			throw new CustomMyRetailException("Product Name was not found for product ID -"+productId, HttpStatus.NOT_FOUND);						
		}
		
		product.setName(name);					
		logger.info("***MR_METRICS*** - retrieveProduct method has been ended at "+new Date().getTime());
		return product;
	}
	
	/*
	 * This method will update the product price for a given ID. It will return Product object.
	 */
	public Product updateProduct(Long productId, Product product) throws CustomMyRetailException {		
		logger.info("***MR_METRICS*** - updateProduct method has been started at "+new Date().getTime());
		if(productId <= 0)
			throw new CustomMyRetailException("Product ID is invalid.", HttpStatus.BAD_REQUEST);
		if(product.getid() != productId) {
			throw new CustomMyRetailException("Product ID in request and param doesn't match.", HttpStatus.CONFLICT);
		}
		
		Product productTemp = productRepository.findOne(productId);
		if(productTemp==null) {
			throw new CustomMyRetailException("Product - "+productId+" was not found.", HttpStatus.NOT_FOUND);
		}
		product = productRepository.save(product);	
		logger.info("***MR_METRICS*** - updateProduct method has been ended at "+new Date().getTime());
		return product;		
	}
	
	/*
	 * This method will generate and return the Redsky URL
	 */
	public URI getURI(Long productId) {
		String url = api_hostName + apiPath + productId;
		
		List<String> paramExcludes = new ArrayList<>();
		paramExcludes.add("taxonomy");
		paramExcludes.add("price");
		paramExcludes.add("promotion");
		paramExcludes.add("bulk_ship");
		paramExcludes.add("rating_and_review_reviews");
		paramExcludes.add("rating_and_review_statistics");
		paramExcludes.add("question_answer_statistics");
		UriComponentsBuilder urilBuild = UriComponentsBuilder.fromHttpUrl(url).queryParam("excludes", paramExcludes);
		return urilBuild.build().encode().toUri();		
	}
	
	/*
	 * Call Redsky REST API to get the product name using product ID. 
	 * 
	 */	
	public String retrieveProductName(Long productId) throws CustomMyRetailException{
		
		ResponseEntity<String> responseEntity = null;		 
		URI uriBuilt = getURI(productId);
		String productName = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			responseEntity = restTemplate.getForEntity(uriBuilt, String.class);
		} catch (HttpClientErrorException e) {
			throw new CustomMyRetailException("Product Name was not found for product ID -"+productId, HttpStatus.NOT_FOUND);			
		}catch(Exception e) {
			throw new RuntimeException("Unknown Exception while trying to get product title for product -"+ productId,e);
		}
		if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			logger.error("Error occured while retrieving product name. Http Status Code is "+responseEntity.getStatusCode());
			return null;
		}
		try {
			JsonNode rootNode = objectMapper.readTree(responseEntity.getBody());
			productName = rootNode.at(apiParamPath).textValue();			
		}catch(JsonProcessingException e) {
			throw new RuntimeException("Error parsing JSON for the product id -"+productId,e);
		}catch(Exception e) {
			throw new RuntimeException("Unknown error while parsing JSON for the product id -"+productId,e);
		}
		return productName;
	}	
	
}
