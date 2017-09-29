package com.myretail.api.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myretail.api.v1.exception.CustomMyRetailException;
import com.myretail.api.v1.model.Product;
import com.myretail.api.v1.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTest {
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void getProduct_Found() throws CustomMyRetailException {
		assertEquals("Beats Solo 2 Wireless - Black", productService.retrieveProduct(16696652L).getName());
	}
	
	@Test(expected=CustomMyRetailException.class)
	public void getProduct_Not_Found() throws CustomMyRetailException {
		productService.retrieveProduct(1652L);
	}
	
	
	
	@Test(expected=CustomMyRetailException.class)	
	public void updateProduct_Not_Found() throws CustomMyRetailException {
		 productService.updateProduct(166L, new Product(166L,"",96.17));
	}
	
	@Test(expected=CustomMyRetailException.class)	
	public void updateProduct_ID_Not_Match() throws CustomMyRetailException {
		 productService.updateProduct(166L, new Product(587L,"",96.17));
	}
}
