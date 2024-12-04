package dev.abarmin.aml.profile.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = ChangeEmailRequest.class, name = "CHANGE_EMAIL")
})
public interface ProfileChangePayload {
}
