package com.springboot.csvtopdf.exception;

/**
 * @author Hiren Solanki
 * 
 * @version 1.0
 * 
 */
public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @author Hiren Solanki
	 * 
	 * @param takes message as a single parameter and Calls super constructor of Exception Class
	 */
	public CustomException(String message) {
		super(message);
	}

}
