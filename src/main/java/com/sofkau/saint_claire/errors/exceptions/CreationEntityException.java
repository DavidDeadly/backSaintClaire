package com.sofkau.saint_claire.errors.exceptions;

public class CreationEntityException extends IllegalStateException {
  public CreationEntityException() {super();}

  public CreationEntityException(String err) {
    super(err);
  }
}
