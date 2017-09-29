package com.myretail.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.api.v1.exception.CustomMyRetailException;
import com.myretail.api.v1.model.Product;
import com.myretail.api.v1.service.ProductService;

@RestController

public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/products/{productId}")
	public Product getProduct(@PathVariable Long productId) throws CustomMyRetailException {		
		return productService.retrieveProduct(productId);		
	}
	
	@RequestMapping(method=RequestMethod.PUT, path="/products/{productId}")
	@ResponseStatus(code=HttpStatus.RESET_CONTENT)
	public Product updateProduct(@RequestBody Product product, @PathVariable Long productId) throws CustomMyRetailException {
		return productService.updateProduct(productId, product);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="/products//{productId}")
	public void deleteProduct() throws HttpRequestMethodNotSupportedException {
		throw new HttpRequestMethodNotSupportedException("DELETE");
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/products//{productId}")
	public void addProduct(@RequestBody Product product, @PathVariable Long productId) throws HttpRequestMethodNotSupportedException {
		throw new HttpRequestMethodNotSupportedException("POST");
	}
	
	@RequestMapping("/products/")
	public Product getProducts(@PathVariable Long productId) throws CustomMyRetailException {
		throw new CustomMyRetailException("Product ID is missing in the request param", HttpStatus.BAD_REQUEST);
	}
}
