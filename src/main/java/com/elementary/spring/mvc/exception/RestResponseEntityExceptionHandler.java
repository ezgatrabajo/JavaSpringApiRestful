package com.elementary.spring.mvc.exception;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CategoriaCustomNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> categoriaCustomNotFound(CategoriaCustomNotFoundException ex)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), "detalle para agregar");
		return new ResponseEntity<ExceptionResponse> (exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	
	
	/*
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> entityNotFound(Exception ex){
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), "detalle para agregar");
		return new ResponseEntity<ExceptionResponse> (exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	
	}*/
	
}
