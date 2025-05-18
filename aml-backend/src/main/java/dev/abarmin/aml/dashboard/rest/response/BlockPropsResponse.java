package dev.abarmin.aml.dashboard.rest.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  property = "blockType"
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = HeaderBlockPropsResponse.class, name = "HEADER_BLOCK")
})
public sealed interface BlockPropsResponse permits
  AvatarBlockPropsResponse,
  HeaderBlockPropsResponse,
  LinkButtonBlockPropsResponse,
  SocialNetworksBlockPropsResponse {
}
