package dev.abarmin.aml.profile.domain;

import lombok.Data;

@Data
public class ChangeLinkRequest implements ProfileChangePayload {
  private String newLink;
}
