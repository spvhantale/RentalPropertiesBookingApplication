package com.swapnil.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(TenatException.class)
	public ResponseEntity<MyError> getTenatException(TenatException exception,WebRequest req){
		
		MyError myError=new MyError(exception.getMessage(), LocalDateTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myError, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(LandLordException.class)
	public ResponseEntity<MyError> getLandLordException(LandLordException exception,WebRequest req){
		
		MyError myError=new MyError(exception.getMessage(), LocalDateTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myError, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(PropertyException.class)
	public ResponseEntity<MyError> getPropertyException(PropertyException exception,WebRequest req){
		
		MyError myError=new MyError(exception.getMessage(), LocalDateTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myError, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(UserSessionException.class)
	public ResponseEntity<MyError> getUserException(UserSessionException exception,WebRequest req){
		
		MyError myError=new MyError(exception.getMessage(), LocalDateTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myError, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyError> getException(Exception exception,WebRequest req){
		
		MyError myError=new MyError(exception.getMessage(), LocalDateTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myError, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyError> getNotHandlerException(NoHandlerFoundException exception,WebRequest req){
		
		MyError myError=new MyError(exception.getMessage(), LocalDateTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myError, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> getMethodException(MethodArgumentNotValidException exception){
		
		MyError myError=new MyError(exception.getBindingResult().getFieldError().getDefaultMessage(), LocalDateTime.now(), "validation failed");
		
		return new ResponseEntity<MyError>(myError, HttpStatus.BAD_REQUEST);
		
	}
	
	
}
