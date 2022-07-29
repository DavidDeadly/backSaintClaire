package com.sofkau.saint_claire.errors;

import com.sofkau.saint_claire.errors.exceptions.CreationEntityException;
import com.sofkau.saint_claire.errors.exceptions.IllegalChangeException;
import com.sofkau.saint_claire.errors.exceptions.InvalidRequestException;
import com.sofkau.saint_claire.errors.exceptions.NotFoundEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException e) {
    return new ResponseEntity<>(new Error(e.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleNotFoundEntityException(NotFoundEntityException e) {
    return new ResponseEntity<>(new Error(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleCreationEntityException(CreationEntityException e) {
    return new ResponseEntity<>(new Error(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler
  public ResponseEntity<Object> handleIllegalChangeException(IllegalChangeException e) {
    return new ResponseEntity<>(new Error(e.getMessage()), HttpStatus.CONFLICT);
  }
}
