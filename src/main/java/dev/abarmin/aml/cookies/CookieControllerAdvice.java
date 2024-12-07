package dev.abarmin.aml.cookies;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CookieControllerAdvice {
  static final String COOKIE_NAME = "cookiesAccepted";

  @ModelAttribute("cookies")
  public CookieModel cookieModel(@CookieValue(value = COOKIE_NAME, defaultValue = "false") boolean accepted) {
    return CookieModel.builder()
      .accepted(accepted)
      .build();
  }
}
