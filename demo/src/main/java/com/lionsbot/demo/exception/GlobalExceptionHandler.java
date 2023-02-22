package com.lionsbot.demo.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	// handling resource not found exception
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> entityNotFoundExceptionHandling(EntityNotFoundException exception){
		ErrorDetails errorDetails = 
				new ErrorDetails(LocalDate.now(), "Entity Not Found", exception.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	// handling validation exception
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> customValidationExceptionHandling(ConstraintViolationException exception){
		ErrorDetails errorDetails = 
				new ErrorDetails(LocalDate.now(), "Validation Error", exception.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
