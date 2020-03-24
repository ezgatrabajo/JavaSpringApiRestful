package com.elementary.spring.mvc.exception;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> entityNotFound(EntityNotFoundException ex)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), "detalle para agregar");
		return new ResponseEntity<ExceptionResponse> (exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
}
