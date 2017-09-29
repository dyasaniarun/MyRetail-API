package com.myretail.api.v1.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyRetailAPIExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(MyRetailAPIExceptionHandler.class);

	protected ResponseEntity<Object> buildResponseEntity(ErrorMessage errorMessage) {
		logger.error(errorMessage.getMessage());
		return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
	}

	@ExceptionHandler(CustomMyRetailException.class)
	public ResponseEntity<Object> requestParamMisMatch(CustomMyRetailException ex) {
		logger.error(ex.getMessage());
		return buildResponseEntity(new ErrorMessage(ex.getHttpStatus(), ex.getMessage(), ex));
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Object> productNotFound(HttpClientErrorException ex) {
		logger.error(ex.getMessage());
		String error = "Product was not found";
		return buildResponseEntity(new ErrorMessage(HttpStatus.NOT_FOUND, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error(ex.getMessage());
		String error = "Malformed JSON request";
		return buildResponseEntity(new ErrorMessage(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Missing Request Parameter";
		logger.warn(error);
		return buildResponseEntity(new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Http Request Method - " + ex.getMethod() + " is not supported";
		logger.warn(error);
		return buildResponseEntity(new ErrorMessage(HttpStatus.NOT_ACCEPTABLE, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Media Type not acceptable";
		logger.warn(error);
		return buildResponseEntity(new ErrorMessage(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = "Invalid Param for Product ID.";
		logger.error(error);
		return buildResponseEntity(new ErrorMessage(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Please send the request in JSON Content Type";
		logger.error(error);
		return buildResponseEntity(new ErrorMessage(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = "Product ID is missing in the request param";
		logger.error(error);
		return buildResponseEntity(new ErrorMessage(HttpStatus.BAD_REQUEST, error, ex));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> globalException(CustomMyRetailException ex) {
		logger.error(ex.getMessage());
		return buildResponseEntity(new ErrorMessage(ex.getHttpStatus(), ex.getMessage(), ex));
	}

}
