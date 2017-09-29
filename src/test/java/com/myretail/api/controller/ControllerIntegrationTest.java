package com.myretail.api.controller;



import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.myretail.api.v1.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)

public class ControllerIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	private TestRestTemplate restTemplate = new TestRestTemplate();
	
	@Test
	public void getProduct() { 
		ResponseEntity<Product> responseEntity = this.restTemplate.getForEntity("http://localhost:"+port+"/products/{productId}", Product.class, 16696652);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void getProduct_Not_Found() {
		long productId = 156;
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(new Product(productId,"Test Book",13.99));
		ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:"+port+"/products/{productId}", HttpMethod.GET, httpEntity, String.class, productId);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	public void updateProduct() {
		long productId = 16696652;
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(new Product(productId,"Updating Product",89.99));
		ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:"+port+"/products/{productId}", HttpMethod.PUT, httpEntity, String.class, productId);
		assertEquals(HttpStatus.RESET_CONTENT, responseEntity.getStatusCode());
	}
	
	@Test
	public void updateProduct_Not_Found() {
		long productId = 345;
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(new Product(productId,"Not FOund Product Book",87.19));
		ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:"+port+"/products/{productId}", HttpMethod.PUT, httpEntity, String.class, productId);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}
	
	@Test
	public void deleteProduct_Not_Allowed() {
		long productId = 345;
		HttpEntity<Product> httpEntity = new HttpEntity<Product>(new Product(productId,"Delete Product Book",87.19));
		ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:"+port+"/products/{productId}", HttpMethod.DELETE, httpEntity, String.class, productId);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
	}
	
	
}
