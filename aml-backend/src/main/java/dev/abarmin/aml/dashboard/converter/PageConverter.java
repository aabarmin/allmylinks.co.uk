package dev.abarmin.aml.dashboard.converter;

import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.model.BlockModel;
import dev.abarmin.aml.dashboard.model.PageModel;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

import static dev.abarmin.aml.dashboard.converter.BlockOrderUtils.isFirst;
import static dev.abarmin.aml.dashboard.converter.BlockOrderUtils.isLast;

@Component
@RequiredArgsConstructor
public class PageConverter {
  private final BlockRepository blockRepository;
  private final BlockConverter blockConverter;

  public PageModel convert(Page page) {
    final List<Block> blocks = blockRepository.findAllVisible(page.id())
      .stream()
      .filter(Predicate.not(Block::isDeleted))
      .toList();

    final List<BlockModel> models = blocks.stream()
      .map(b -> blockConverter.convert(b, isFirst(b, blocks), isLast(b, blocks)))
      .toList();

    return new PageModel(
      page.id(),
      page.title(),
      page.pageProps(),
      models
    );
  }
}
