package dev.abarmin.aml.dashboard.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = HeaderBlockProps.class, name = "HEADER_BLOCK"),
})
public interface BlockProps {
  BlockType type();
}
