package dev.abarmin.aml.profile.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = ChangeEmailRequest.class, name = "CHANGE_EMAIL"),
  @JsonSubTypes.Type(value = ChangeLinkRequest.class, name = "CHANGE_LINK"),
  @JsonSubTypes.Type(value = DeactivateProfileRequest.class, name = "PROFILE_DEACTIVATE")
})
public interface ProfileChangePayload {
}
