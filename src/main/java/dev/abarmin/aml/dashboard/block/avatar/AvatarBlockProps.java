package dev.abarmin.aml.dashboard.block.avatar;

import dev.abarmin.aml.dashboard.domain.BlockProps;
import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.file.FileId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvatarBlockProps implements BlockProps {
  public static final FileId DEFAULT_AVATAR = FileId.resource("/img/avatar_placeholder.png");

  private FileId fileId;

  @Override
  public BlockType type() {
    return BlockType.AVATAR_BLOCK;
  }
}
