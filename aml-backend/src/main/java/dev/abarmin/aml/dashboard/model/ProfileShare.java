package dev.abarmin.aml.dashboard.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileShare {
  private String onTwitter;
  private String onFacebook;
  private String onLinkedin;
}
