package com.system.training.exception;

public class InvalidStateTransitionException extends Exception{
	
	
	private static final long serialVersionUID = 1L;

	public InvalidStateTransitionException(String message) {
		super(message);
	}

}
