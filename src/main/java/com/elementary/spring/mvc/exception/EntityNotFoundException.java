package com.elementary.spring.mvc.exception;

public class EntityNotFoundException extends RuntimeException{
	public EntityNotFoundException(String msg) {
		super(msg);
	}
}
