package dev.abarmin.aml.dashboard.rest.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.dashboard.rest.response.AvatarBlockPropsResponse;
import dev.abarmin.aml.dashboard.rest.response.BlockPropsResponse;
import dev.abarmin.aml.dashboard.rest.response.HeaderBlockPropsResponse;
import dev.abarmin.aml.dashboard.rest.response.LinkButtonBlockPropsResponse;
import dev.abarmin.aml.dashboard.rest.response.SocialNetworksBlockPropsResponse;

public record BlockPropsUpdateRequest(
  long blockId,
  BlockType blockType,
  @JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "blockType",
    include = JsonTypeInfo.As.EXTERNAL_PROPERTY
  )
  @JsonSubTypes({
    @JsonSubTypes.Type(value = HeaderBlockPropsResponse.class, name = "HEADER_BLOCK"),
    @JsonSubTypes.Type(value = AvatarBlockPropsResponse.class, name = "AVATAR_BLOCK"),
    @JsonSubTypes.Type(value = LinkButtonBlockPropsResponse.class, name = "BUTTON_BLOCK"),
    @JsonSubTypes.Type(value = SocialNetworksBlockPropsResponse.class, name = "SOCIAL_NETWORKS_BLOCK")
  })
  BlockPropsResponse blockProps
) { }
