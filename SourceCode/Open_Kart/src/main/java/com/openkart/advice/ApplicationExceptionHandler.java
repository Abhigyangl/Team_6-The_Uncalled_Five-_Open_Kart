package com.openkart.advice;

import java.util.HashMap

;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.openkart.exception.AlreadyExistsException;
import com.openkart.exception.NoSuchElementException;

@RestControllerAdvice 
@ControllerAdvice
//Exception Class for Validation.
public class ApplicationExceptionHandler 
{
	
@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String, String> handleInvalidArgumaentr(MethodArgumentNotValidException ex)
{
	
	Map<String, String> errorMap=new HashMap<>();
	ex.getBindingResult().getFieldErrors().forEach(error->{
		errorMap.put(error.getField(),error.getDefaultMessage());
	});
	return errorMap;
}


//Exception Controller for NoSuchElementException
@ExceptionHandler(value = NoSuchElementException.class)
public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException exception) 
  {
	if(exception.getId()==-1)
		return new ResponseEntity<>(exception.getMsg(),HttpStatus.BAD_REQUEST);
	else
	    return new ResponseEntity<>(exception.getMsg()+" with  ID "+exception.getId()+" does not Exist", HttpStatus.NOT_FOUND);
  }

//Exception Controller for AlreadyExists
@ExceptionHandler(value = AlreadyExistsException.class)
public ResponseEntity<Object> handleAlreadyExistsException(AlreadyExistsException exception) 
{
	if(exception.getId()==-1)
		return new ResponseEntity<>(exception.getMsg(),HttpStatus.BAD_REQUEST);
	else
	    return new ResponseEntity<>(exception.getMsg()+" with Id "+exception.getId()+" already Exist", HttpStatus.BAD_REQUEST);
}

}