package dev.abarmin.aml.e2e.page.registration;

import dev.abarmin.aml.e2e.page.login.LoginForm;
import lombok.Value;

@Value
public class RegistrationForm {
  String name;
  String email;
  String password;
  String shortLink;
  boolean tac;

  public LoginForm toLoginForm() {
    return new LoginForm(
      email,
      password
    );
  }
}
