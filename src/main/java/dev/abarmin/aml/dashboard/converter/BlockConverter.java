package dev.abarmin.aml.dashboard.converter;

import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.model.BlockModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlockConverter {
  private final BlockTypeConverter typeConverter;

  public BlockModel convert(Block block) {
    return new BlockModel(
      block.id(),
      typeConverter.convert(block.type()),
      block.props()
    );
  }
}
