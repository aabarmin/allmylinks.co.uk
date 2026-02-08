package dev.abarmin.aml.profile.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class DeactivateProfileRequest implements ProfileChangePayload {
  private String reason;
}
