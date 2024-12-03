package dev.abarmin.aml.dashboard.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.abarmin.aml.dashboard.block.avatar.AvatarBlockProps;
import dev.abarmin.aml.dashboard.block.header.HeaderBlockProps;
import dev.abarmin.aml.dashboard.block.link.LinkButtonBlockProps;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = HeaderBlockProps.class, name = "HEADER_BLOCK"),
  @JsonSubTypes.Type(value = LinkButtonBlockProps.class, name = "BUTTON_BLOCK"),
  @JsonSubTypes.Type(value = AvatarBlockProps.class, name = "AVATAR_BLOCK")
})
public interface BlockProps {
  BlockType type();
}
