package dev.abarmin.aml.dashboard.model;

import dev.abarmin.aml.dashboard.domain.BlockProps;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlockModel {
  private long blockId;
  private long pageId;
  private BlockTypeModel blockType;
  private BlockProps blockProps;
  private boolean canMoveUp;
  private boolean canMoveDown;
}
