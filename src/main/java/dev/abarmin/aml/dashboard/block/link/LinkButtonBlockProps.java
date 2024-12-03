package dev.abarmin.aml.dashboard.block.link;

import dev.abarmin.aml.dashboard.domain.BlockProps;
import dev.abarmin.aml.dashboard.domain.BlockType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkButtonBlockProps implements BlockProps {
  private String text;
  private String link;

  @Override
  public BlockType type() {
    return BlockType.BUTTON_BLOCK;
  }
}
