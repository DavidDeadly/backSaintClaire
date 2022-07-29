package com.sofkau.saint_claire.errors.exceptions;

public class InvalidRequestException extends IllegalStateException{
  public InvalidRequestException() {
    super();
  }

  public InvalidRequestException(String err) {
    super(err);
  }
}
