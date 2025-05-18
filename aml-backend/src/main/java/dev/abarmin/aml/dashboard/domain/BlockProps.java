package dev.abarmin.aml.dashboard.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = HeaderBlockProps.class, name = "HEADER_BLOCK"),
  @JsonSubTypes.Type(value = LinkButtonBlockProps.class, name = "BUTTON_BLOCK"),
  @JsonSubTypes.Type(value = AvatarBlockProps.class, name = "AVATAR_BLOCK"),
  @JsonSubTypes.Type(value = SocialNetworksBlockProps.class, name = "SOCIAL_NETWORKS_BLOCK")
})
public sealed interface BlockProps permits
  HeaderBlockProps,
  LinkButtonBlockProps,
  AvatarBlockProps,
  SocialNetworksBlockProps {

  
}
