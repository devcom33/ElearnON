package com.system.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

	public CourseNotFoundException(String message) {
        super(message);
    }
}