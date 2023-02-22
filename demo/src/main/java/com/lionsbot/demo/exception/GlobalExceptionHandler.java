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
				new ErrorDetails(LocalDate.now(), "Resource Not Found", exception.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	// handling entity already exist exception
	@ExceptionHandler(EntityAlreadyExistException.class)
	public ResponseEntity<?> entityAlreadyExistException(EntityAlreadyExistException exception){
		ErrorDetails errorDetails = 
				new ErrorDetails(LocalDate.now(), "Entity already exist", exception.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.IM_USED);
	}
	
	// handling validation exception
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> customValidationExceptionHandling(ConstraintViolationException exception){
		ErrorDetails errorDetails = 
				new ErrorDetails(LocalDate.now(), "Validation Error", exception.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
//	// handling Other exceptions
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> globalExceptionHandling(Exception exception){
//		ErrorDetails errorDetails = 
//				new ErrorDetails(LocalDate.now(), "Other Service Error", exception.getMessage());
//		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
}
