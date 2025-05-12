package dev.abarmin.aml.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {
  @NotEmpty
  private String name;

  @Builder.Default
  private boolean emailEditable = true;

  @Email
  @NotEmpty
  private String email;

  @Builder.Default
  private boolean passwordRequired = true;

  private String password;

  @NotEmpty
  @Pattern(regexp = "^[a-z0-9]+$")
  private String link;

  @Builder.Default
  private boolean acceptTerms = true;
}
