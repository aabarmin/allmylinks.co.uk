package dev.abarmin.aml.dashboard.model;

import dev.abarmin.aml.dashboard.domain.BlockProps;

public record BlockModel(
  long blockId,
  BlockTypeModel blockType,
  BlockProps blockProps
) {
}
