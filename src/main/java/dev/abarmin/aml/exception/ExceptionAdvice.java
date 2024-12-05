package dev.abarmin.aml.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
  @ExceptionHandler(Exception.class)
  public String serverError() {
    return "exception/error-5xx";
  }
}
