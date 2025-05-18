package dev.abarmin.aml.dashboard.rest;

import dev.abarmin.aml.dashboard.SessionService;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import dev.abarmin.aml.dashboard.rest.converter.DashboardResponseConverter;
import dev.abarmin.aml.dashboard.rest.response.DashboardResponse;
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
  private final DashboardResponseConverter dashboardConverter;

  @GetMapping
  public DashboardResponse dashboardModel(Authentication authentication) {
    final Profile profile = sessionService.getProfile(authentication);
    final Page page = getCurrentPage(profile, null);
    return dashboardConverter.convert(profile, page);
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
