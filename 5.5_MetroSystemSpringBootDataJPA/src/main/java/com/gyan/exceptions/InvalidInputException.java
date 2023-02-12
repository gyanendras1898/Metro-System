package com.gyan.exceptions;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class InvalidInputException extends Exception{
	
	public InvalidInputException() {
		super("Invalid Input : Please input number only.");
	}
	
	public InvalidInputException(String message) {
		super(message);
	}
}
