/* Classe do Spring "@ControllerAdvice" responsável por interceptar @ExceptionHandler
 * as exceções na camada de Resource(Controller) para não ficar implementando try/cacth
 * em todo método da camada que precise fazer um tratamento de execeção. */
package com.devsuperior.dslearn.resourses.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dslearn.services.execeptions.DatabaseException;
import com.devsuperior.dslearn.services.execeptions.ForbiddenException;
import com.devsuperior.dslearn.services.execeptions.ResourceNotFoundException;
import com.devsuperior.dslearn.services.execeptions.UnauthorizedException;

@ControllerAdvice
public class ResourceExceptionHandler {
	/* resposta de requisição onde o payload(conteudo) da resposta será um objeto do
	 * tipo StandardError, pois a estrutura de erro será customizado. */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status .value());
		err.setError("Resource Not Found!");
		err.setMessage(e.getMessage()); //mensagem setado no service
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> entityNotFound(DatabaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("DataBase Exception!");
		err.setMessage(e.getMessage()); //mensagem setado no service
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Validation Exception!");
		err.setMessage(e.getMessage()); //mensagem setado no service
		err.setPath(request.getRequestURI());
		
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<OAuth2CustomError> forbidden(ForbiddenException e, HttpServletRequest request) {
		OAuth2CustomError err = new OAuth2CustomError("Forbidden!",e.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<OAuth2CustomError> unauthorized(UnauthorizedException e, HttpServletRequest request) {
		OAuth2CustomError err = new OAuth2CustomError("Unauthorized!", e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
}
