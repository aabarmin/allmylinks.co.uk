package dev.abarmin.aml.dashboard.converter;

import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.model.BlockModel;
import dev.abarmin.aml.dashboard.model.PageModel;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class PageConverter {
  private final BlockRepository blockRepository;
  private final BlockConverter blockConverter;

  public PageModel convert(Page page) {
    final List<BlockModel> pageBlocks = blockRepository.findAllByPageId(page.id(), Sort.by(Sort.Direction.ASC, "block_order"))
      .stream()
      .filter(Predicate.not(Block::isDeleted))
      .sorted(Comparator.comparing(Block::order))
      .map(blockConverter::convert)
      .toList();

    return new PageModel(
      page.id(),
      page.title(),
      pageBlocks
    );
  }
}
