package dev.abarmin.aml.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PreRegistrationForm {
  private boolean preRegistration = true;

  @Email
  @NotEmpty
  private String email;
}
