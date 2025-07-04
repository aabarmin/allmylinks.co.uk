package dev.abarmin.aml;

import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import dev.abarmin.aml.registration.TestCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class TestPageService {
  private final PageRepository pageRepository;
  private final BlockRepository blockRepository;

  public long getHomePageId(TestCredentials user) {
    final Page homePage = pageRepository.findAllByProfileId(user.currentProfileId())
      .stream()
      .filter(not(Page::isDeleted))
      .filter(Page::isHome)
      .findFirst()
      .orElseThrow();

    return homePage.id();
  }

  public Collection<Block> getHomePageBlocks(TestCredentials user) {
    return getPageBlocks(getHomePageId(user));
  }

  public Collection<Block> getPageBlocks(long pageId) {
    return blockRepository.findAllByPageId(pageId);
  }
}
