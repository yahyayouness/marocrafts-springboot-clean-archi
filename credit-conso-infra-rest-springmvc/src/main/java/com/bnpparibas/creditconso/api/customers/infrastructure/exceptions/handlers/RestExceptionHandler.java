package com.bnpparibas.creditconso.api.customers.infrastructure.exceptions.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bnpparibas.creditconso.api.customers.infrastructure.exceptions.commons.ErrorDetails;

/**
 * Component to implement global exception handling and customize the response
 * based on the exception type.
 *
 */

@ControllerAdvice
@RestController
public class RestExceptionHandler{

	@Autowired
	private MessageSource msgSource;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<List<ErrorDetails>> processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> errors = result.getFieldErrors();
		List<ErrorDetails> errorDetails = new ArrayList<>();
		for (FieldError error : errors) {
			Locale currentLocale = LocaleContextHolder.getLocale();
			String msg = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
			ErrorDetails errorDetail = new ErrorDetails(new Date(), msg, error.getField());
			errorDetails.add(errorDetail);
		}
		return new ResponseEntity<List<ErrorDetails>>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}