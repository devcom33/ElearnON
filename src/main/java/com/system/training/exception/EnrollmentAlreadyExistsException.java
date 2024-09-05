package com.system.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class EnrollmentAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public EnrollmentAlreadyExistsException(String message)
	{
		super(message);
	}

}
