package dev.abarmin.aml.profile.domain;

import lombok.Data;

@Data
public class ChangeEmailRequest implements ProfileChangePayload {
  private String newEmail;
}
