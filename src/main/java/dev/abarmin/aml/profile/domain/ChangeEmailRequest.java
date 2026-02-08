package dev.abarmin.aml.profile.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ChangeEmailRequest implements ProfileChangePayload {
  private String newEmail;
}
