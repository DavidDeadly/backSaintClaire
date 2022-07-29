package com.sofkau.saint_claire.errors.exceptions;

public class IllegalChangeException extends IllegalStateException {
  public IllegalChangeException() {super();}

  public IllegalChangeException(String err) {
    super(err);
  }
}
