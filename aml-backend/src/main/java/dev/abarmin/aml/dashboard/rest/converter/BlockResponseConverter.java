package dev.abarmin.aml.dashboard.rest.converter;

import dev.abarmin.aml.dashboard.converter.BlockTypeConverter;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.model.BlockModel;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.dashboard.rest.response.BlockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

import static dev.abarmin.aml.dashboard.converter.BlockOrderUtils.isFirst;
import static dev.abarmin.aml.dashboard.converter.BlockOrderUtils.isLast;
import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class BlockResponseConverter {

  private final BlockRepository blockRepository;
  private final BlockTypeConverter typeConverter;
  private final BlockPropsConverter propsConverter;

  public BlockResponse convert(Block block) {
    final List<Block> blocks = blockRepository.findAllByPageId(block.pageId())
      .stream()
      .filter(not(Block::isDeleted))
      .sorted(Comparator.comparing(Block::order))
      .toList();

    return convert(block, isFirst(block, blocks), isLast(block, blocks));
  }

  public BlockResponse convert(Block block, boolean isFirst, boolean isLast) {
    return new BlockResponse(
      block.id(),
      block.pageId(),
      typeConverter.convert(block.type()),
      propsConverter.convert(block.props()),
      !isFirst,
      !isLast
    );
  }

}
