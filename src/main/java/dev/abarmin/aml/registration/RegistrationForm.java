package dev.abarmin.aml.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationForm {
  @NotEmpty
  private String name;

  @Email
  private String email;

  @NotEmpty
  @Size(min = 3, max = 10)
  private String password;

  @NotEmpty
  @Pattern(regexp = "^[a-z]+$")
  private String link;

  private boolean acceptTerms;
}
