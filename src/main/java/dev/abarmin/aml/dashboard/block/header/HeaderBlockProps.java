package dev.abarmin.aml.dashboard.block.header;

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
public class HeaderBlockProps implements BlockProps {
  private String text;
  private HeaderLevel level;
  private TextAlignment alignment;

  @Override
  public BlockType type() {
    return BlockType.HEADER_BLOCK;
  }
}
