package dev.abarmin.aml.profile.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class ChangeLinkRequest implements ProfileChangePayload {
  private String newLink;
}
