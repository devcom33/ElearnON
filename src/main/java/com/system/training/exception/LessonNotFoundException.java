package com.system.training.exception;

public class LessonNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public LessonNotFoundException(String message)
	{
		super(message);
	}
}
