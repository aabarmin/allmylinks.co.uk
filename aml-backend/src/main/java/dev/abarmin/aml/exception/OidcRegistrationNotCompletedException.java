package dev.abarmin.aml.exception;

public class OidcRegistrationNotCompletedException extends RuntimeException {
  public OidcRegistrationNotCompletedException(String message) {
    super(message);
  }
}
