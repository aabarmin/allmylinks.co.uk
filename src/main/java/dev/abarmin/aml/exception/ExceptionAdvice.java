package dev.abarmin.aml.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler(OidcRegistrationNotCompletedException.class)
  public String oidcRegistrationNotCompleted() {
    return "redirect:/register/oidc";
  }

  @ExceptionHandler(Exception.class)
  public String serverError(Exception e) {
    log.warn("Server error", e);
    return "exception/error-5xx";
  }
}
