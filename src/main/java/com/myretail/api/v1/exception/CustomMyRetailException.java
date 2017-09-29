package com.myretail.api.v1.exception;

import org.springframework.http.HttpStatus;

public class CustomMyRetailException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8295857180360010507L;
	private String message;
	private HttpStatus httpStatus;
	
	/**
	 * @param message
	 * @param httpStatus
	 */
	public CustomMyRetailException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
