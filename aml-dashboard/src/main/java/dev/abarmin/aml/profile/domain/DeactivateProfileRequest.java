package dev.abarmin.aml.profile.domain;

import lombok.Data;

@Data
public class DeactivateProfileRequest implements ProfileChangePayload {
  private String reason;
}
