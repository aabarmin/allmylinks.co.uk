package dev.abarmin.aml.backoffice.profile.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileChangeRequestRow {

  private long id;
  private String userEmail;
  private String profileName;
  private String profileLink;
  private String requestType;
  private String requestStatus;

}
