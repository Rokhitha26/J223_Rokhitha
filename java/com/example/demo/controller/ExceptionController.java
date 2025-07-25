package com.example.demo.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ResourceNotFoundException;

@RestControllerAdvice

public class ExceptionController {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ErrorCode ResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorCode msg= new ErrorCode(HttpStatus.NOT_FOUND.value(), new Date(),ex.getMessage(),request.getDescription(false));
		return msg;
	}
}
