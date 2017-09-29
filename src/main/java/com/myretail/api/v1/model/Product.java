package com.myretail.api.v1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document
@JsonInclude(Include.NON_DEFAULT)
public class Product {
	
	@Id
	private long id;
	@Transient	
	private String name;
	private double current_price;
	
	public Product() {
		 
	}
	
	public Product(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Product(long id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.current_price = price;
	}
	public long getid() {
		return id;
	}
	public void setid(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public double getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(double current_price) {
		this.current_price = current_price;
	}

	@Override
	public String toString() {
		return "Product [id=" + this.id + ", name=" + this.name + ", current_price=" + this.current_price + "]";
	}
	
}
