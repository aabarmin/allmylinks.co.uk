package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.converter.DashboardModelConverter;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.model.DashboardModel;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import dev.abarmin.aml.registration.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/dashboard")
public class DashboardRestController {

  private final SessionService sessionService;
  private final PageRepository pageRepository;
  private final DashboardModelConverter dashboardModelConverter;

  @GetMapping
  public DashboardModel dashboardModel(Authentication authentication) {
    final Profile profile = sessionService.getProfile(authentication);
    final Page page = getCurrentPage(profile, null);
    return dashboardModelConverter.convert(profile, page);
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
