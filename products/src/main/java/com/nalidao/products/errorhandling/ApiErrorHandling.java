package com.nalidao.products.errorhandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nalidao.products.errorhandling.exception.ProdutoNotFoundException;
import com.nalidao.products.errorhandling.utils.ApiErrorDetails;
import com.nalidao.products.errorhandling.utils.FormErrorDetails;

@ControllerAdvice
public class ApiErrorHandling {
	
	@ExceptionHandler(ProdutoNotFoundException.class)
	private ResponseEntity<ApiErrorDetails> handleProdutoNotFoundException(ProdutoNotFoundException e) {
		ApiErrorDetails details = new ApiErrorDetails(
				                      "Produto não encontrado",
				                      HttpStatus.NOT_FOUND.toString(),
				                      e.getLocalizedMessage(),
				                      e.getClass().getPackage().toString(),
				                      LocalDateTime.now());
		
		return new ResponseEntity<ApiErrorDetails>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<List<FormErrorDetails>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<FormErrorDetails> detalhes = new ArrayList<FormErrorDetails>();
		
		List<FieldError> campos = e.getBindingResult().getFieldErrors();
		campos.forEach(campo -> {
			FormErrorDetails detalhe = new FormErrorDetails(
											"Erro de formulário",
											HttpStatus.BAD_REQUEST.toString(),
											campo.getDefaultMessage(),
											e.getClass().getPackage().toString(),
											LocalDateTime.now(),
											campo.getField());
			detalhes.add(detalhe);
		});
		
		return new ResponseEntity<List<FormErrorDetails>>(detalhes, HttpStatus.BAD_REQUEST);
	}
	
}
