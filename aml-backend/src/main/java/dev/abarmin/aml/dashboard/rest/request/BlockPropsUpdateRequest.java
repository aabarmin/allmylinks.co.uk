package dev.abarmin.aml.dashboard.rest.request;

import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.dashboard.rest.response.BlockPropsResponse;

public record BlockPropsUpdateRequest(
  long blockId,
  BlockType blockType,
  BlockPropsResponse blockProps
) { }
