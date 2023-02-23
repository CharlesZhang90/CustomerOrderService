package com.lionsbot.demo.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

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
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customValidationExceptionHandling(MethodArgumentNotValidException exception){
		ErrorDetails errorDetails = 
				new ErrorDetails(LocalDate.now(), "Validation Error", exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	// handling Other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception){
		ErrorDetails errorDetails = 
				new ErrorDetails(LocalDate.now(), "Internal Service Error", exception.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
