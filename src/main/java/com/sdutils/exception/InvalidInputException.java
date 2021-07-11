package com.sdutils.exception;

/**
 * 
 * @author Senthilnathan
 * 
 * This is a custom exception to handle any validation exceptions.
 *
 */

public class InvalidInputException extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;

	public InvalidInputException(String message) {
		
		super(message);
	}


}
