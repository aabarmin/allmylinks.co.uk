package dev.abarmin.aml.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler(OidcRegistrationNotCompletedException.class)
  public String oidcRegistrationNotCompleted() {
    return "redirect:/register/oidc";
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String serverError(Exception e) {
    log.warn("Server error", e);
    return "exception/error-5xx";
  }
}
