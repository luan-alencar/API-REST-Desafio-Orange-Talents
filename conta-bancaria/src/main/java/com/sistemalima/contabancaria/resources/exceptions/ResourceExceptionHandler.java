package com.sistemalima.contabancaria.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sistemalima.contabancaria.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	// metodo para tratamento de erro / recurso não encontrado 404
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError erro = new StandardError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(status.value());
		erro.setError("Entity not found");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
		
	// metodo para retorna resposta http 422 / alguma entidade não foi possivel ser processada
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError erro = new ValidationError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(status.value());
		erro.setError("Validation exception");
		erro.setMessage(e.getMessage());
		erro.setPath(request.getRequestURI());
		
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			erro.addErro(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(erro);
	}
	

}
