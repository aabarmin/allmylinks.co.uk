package dev.abarmin.aml.dashboard.converter;

import dev.abarmin.aml.dashboard.block.avatar.AvatarBlockProps;
import dev.abarmin.aml.dashboard.block.avatar.PublicAvatarBlockProps;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.model.BlockModel;
import dev.abarmin.aml.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlockConverter {
  private final BlockTypeConverter typeConverter;
  private final FileService fileService;

  public BlockModel convert(Block block) {
    if (block.props() instanceof AvatarBlockProps avatar) {
      final String publicUrl = fileService.getPublicUrl(avatar.getFileId());
      return new BlockModel(
        block.id(),
        typeConverter.convert(block.type()),
        new PublicAvatarBlockProps(publicUrl)
      );
    }

    return new BlockModel(
      block.id(),
      typeConverter.convert(block.type()),
      block.props()
    );
  }
}
