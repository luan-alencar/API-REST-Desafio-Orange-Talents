package com.sistemalima.contabancaria.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	// metodo para adicionar os erros na lista de erros
	
	public void addErro(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
