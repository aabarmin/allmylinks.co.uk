package dev.abarmin.aml.dashboard.converter;

import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.model.BlockModel;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

import static dev.abarmin.aml.dashboard.converter.BlockOrderUtils.isFirst;
import static dev.abarmin.aml.dashboard.converter.BlockOrderUtils.isLast;
import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class BlockConverter {
  private final BlockTypeConverter typeConverter;
  private final BlockRepository blockRepository;

  public Block convert(BlockModel model) {
    final Block currentBlock = blockRepository.findById(model.getBlockId()).orElseThrow();
    return currentBlock.withProps(model.getBlockProps());
  }

  public BlockModel convert(Block block) {
    final List<Block> blocks = blockRepository.findAllByPageId(block.pageId())
      .stream()
      .filter(not(Block::isDeleted))
      .sorted(Comparator.comparing(Block::order))
      .toList();

    return convert(block, isFirst(block, blocks), isLast(block, blocks));
  }

  public BlockModel convert(Block block, boolean isFirst, boolean isLast) {
    return new BlockModel(
      block.id(),
      block.pageId(),
      typeConverter.convert(block.type()),
      block.props(),
      !isFirst,
      !isLast
    );
  }
}
