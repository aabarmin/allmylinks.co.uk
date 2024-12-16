package dev.abarmin.aml.dashboard.converter;

import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.dashboard.model.BlockTypeModel;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BlockTypeConverter {
  public Collection<BlockTypeModel> convert(Collection<BlockType> blocks) {
    return blocks.stream()
      .map(this::convert)
      .toList();
  }

  public BlockTypeModel convert(BlockType type) {
    return new BlockTypeModel(
      type.getName(),
      type.getIcon(),
      type.name(),
      type.getPreviewComponent(),
      type.getConfigComponent()
    );
  }
}
