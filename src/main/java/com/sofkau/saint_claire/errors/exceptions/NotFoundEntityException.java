package com.sofkau.saint_claire.errors.exceptions;

public class NotFoundEntityException extends IllegalStateException {
  public NotFoundEntityException() {
    super();
  }

  public NotFoundEntityException(String err) {
    super(err);
  }
}
