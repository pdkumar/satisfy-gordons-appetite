package com.restaurant.exceptions;

public class RestaurantServiceException extends RuntimeException {

	private static final long serialVersionUID = -5962857305504710741L;
	
	public RestaurantServiceException(String message) {
		super(message);
	}
	
	public RestaurantServiceException(Throwable throwable) {
		super(throwable);
	}
	
	public RestaurantServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
