package com.elementary.spring.mvc.exception;

public class CategoriaNotFoundException extends RuntimeException {

	public CategoriaNotFoundException(Integer id) {
		super("Could not find employee " + id);
	}
}