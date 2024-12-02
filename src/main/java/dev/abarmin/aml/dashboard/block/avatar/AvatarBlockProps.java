package dev.abarmin.aml.dashboard.block.avatar;

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
public class AvatarBlockProps implements BlockProps {
  private String imageUrl;

  @Override
  public BlockType type() {
    return BlockType.AVATAR_BLOCK;
  }
}
