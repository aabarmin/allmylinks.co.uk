package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.dashboard.converter.BlockConverter;
import dev.abarmin.aml.dashboard.converter.DashboardModelConverter;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.model.BlockModel;
import dev.abarmin.aml.dashboard.model.DashboardModel;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import dev.abarmin.aml.registration.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice(assignableTypes = {
  DashboardController.class,
  BlockUpdateHandler.class
})
@RequiredArgsConstructor
public class DashboardControllerAdvice {
  private final SessionService sessionService;
  private final DashboardModelConverter dashboardConverter;
  private final PageRepository pageRepository;
  private final BlockRepository blockRepository;
  private final BlockConverter blockConverter;
  private final AppConfiguration configuration;

  @ModelAttribute("model")
  public DashboardModel model(Authentication authentication,
                              @PathVariable(value = "pageId", required = false) Long pageId) {
    final Profile profile = sessionService.getProfile(authentication);
    final Page currentPage = getCurrentPage(profile, pageId);
    return dashboardConverter.convert(profile, currentPage);
  }

  @ModelAttribute("currentBlock")
  private BlockModel currentBlock(@PathVariable(value = "blockId", required = false) Long blockId) {
    if (Objects.isNull(blockId)) {
      return null;
    }
    final Block currentBlock = blockRepository.findById(blockId)
      .orElseThrow(() -> new IllegalArgumentException("Block not found"));
    return blockConverter.convert(currentBlock);
  }

  @ModelAttribute("allowedImageTypes")
  public String allowedImageTypes() {
    return String.join(",", configuration.getImageService().getAllowedTypes());
  }

  private Page getCurrentPage(Profile profile, Long pageId) {
    if (Objects.nonNull(pageId)) {
      return pageRepository.findById(pageId)
        .orElseThrow(() -> new IllegalArgumentException("Page not found"));
    }
    return pageRepository.findAllByProfileId(profile.id())
      .stream()
      .filter(Page::isHome)
      .findFirst()
      .orElseThrow(() -> new IllegalStateException("Home page not found"));
  }
}
