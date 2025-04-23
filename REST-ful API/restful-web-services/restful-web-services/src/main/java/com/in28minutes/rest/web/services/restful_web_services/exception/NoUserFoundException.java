package com.in28minutes.rest.web.services.restful_web_services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoUserFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoUserFoundException(String message) {
		super(message);
	}
}
