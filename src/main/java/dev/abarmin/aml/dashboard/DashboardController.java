package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.converter.DashboardModelConverter;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.model.DashboardModel;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import dev.abarmin.aml.registration.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping({
  "/private/dashboard",
  "/private/dashboard/{pageId}"
})
public class DashboardController {
  private final SessionService sessionService;
  private final DashboardModelConverter modelProvider;
  private final PageRepository pageRepository;

  @ModelAttribute("model")
  public DashboardModel model(Authentication authentication,
                              @PathVariable(value = "pageId", required = false) Long pageId) {
    final Profile profile = sessionService.getProfile(authentication);
    final Page homePage = getCurrentPage(profile, pageId);

    return modelProvider.convert(profile, homePage);
  }

  @GetMapping
  public String home() {
    return "private/dashboard";
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
