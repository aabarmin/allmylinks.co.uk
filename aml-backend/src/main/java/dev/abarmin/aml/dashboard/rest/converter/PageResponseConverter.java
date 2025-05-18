package dev.abarmin.aml.dashboard.rest.converter;

import dev.abarmin.aml.dashboard.domain.BasicPageProps;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.domain.PageProps;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.dashboard.rest.response.BlockResponse;
import dev.abarmin.aml.dashboard.rest.response.PagePropsResponse;
import dev.abarmin.aml.dashboard.rest.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

import static dev.abarmin.aml.dashboard.converter.BlockOrderUtils.isFirst;
import static dev.abarmin.aml.dashboard.converter.BlockOrderUtils.isLast;

@Component
@RequiredArgsConstructor
public class PageResponseConverter {

  private final BlockRepository blockRepository;
  private final BlockResponseConverter blockConverter;

  public PageResponse convert(Page page) {
    final List<Block> blocks = blockRepository.findAllVisible(page.id())
      .stream()
      .filter(Predicate.not(Block::isDeleted))
      .toList();

    final List<BlockResponse> models = blocks.stream()
      .map(b -> blockConverter.convert(b, isFirst(b, blocks), isLast(b, blocks)))
      .toList();

    return new PageResponse(
      page.id(),
      page.title(),
      convert(page.pageProps()),
      models
    );
  }

  private PagePropsResponse convert(PageProps props) {
    return switch(props) {
      case BasicPageProps p -> convert(p);
      default -> throw new IllegalArgumentException("Unsupported page props type: " + props.getClass());
    };
  }

  private PagePropsResponse convert(BasicPageProps props) {
    return new PagePropsResponse(
      props.getBackgroundColor()
    );
  }

}
