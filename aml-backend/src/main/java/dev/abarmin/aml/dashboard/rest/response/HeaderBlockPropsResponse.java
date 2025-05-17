package dev.abarmin.aml.dashboard.rest.response;

import dev.abarmin.aml.dashboard.block.header.HeaderLevel;
import dev.abarmin.aml.dashboard.block.header.TextAlignment;

public record HeaderBlockPropsResponse(
  String text,
  HeaderLevel level,
  TextAlignment alignment
) implements BlockPropsResponse {
}
