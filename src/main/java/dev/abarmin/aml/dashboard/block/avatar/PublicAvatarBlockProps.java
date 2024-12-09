package dev.abarmin.aml.dashboard.block.avatar;

import dev.abarmin.aml.dashboard.domain.BlockProps;
import dev.abarmin.aml.dashboard.domain.BlockType;
import lombok.Data;
import lombok.Value;

@Data
@Value
public class PublicAvatarBlockProps implements BlockProps {
  String imageUrl;

  @Override
  public BlockType type() {
    return BlockType.AVATAR_BLOCK;
  }
}
