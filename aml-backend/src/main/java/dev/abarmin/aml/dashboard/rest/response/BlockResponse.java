package dev.abarmin.aml.dashboard.rest.response;

import dev.abarmin.aml.dashboard.model.BlockTypeModel;

public record BlockResponse(
  long blockId,
  long pageId,
  BlockTypeModel blockType,
  BlockPropsResponse blockProps,
  boolean canMoveUp,
  boolean canMoveDown
) {
}
