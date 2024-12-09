package dev.abarmin.aml.profile.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileModel {
  private String link;
  private String currentEmail;
  private boolean hasActiveEmailChangeRequest;
  private boolean hasActiveDeactivationRequest;
  private boolean hasActiveLinkChangeRequest;
}
