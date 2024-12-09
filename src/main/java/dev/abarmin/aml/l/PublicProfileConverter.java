package dev.abarmin.aml.l;

import dev.abarmin.aml.dashboard.converter.PageConverter;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.model.PageModel;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import dev.abarmin.aml.registration.domain.Profile;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class PublicProfileConverter {
  private final PageConverter pageConverter;
  private final PageRepository pageRepository;

  public PublicProfileModel convert(@NonNull Profile profile) {
    final PageModel pageModel = pageRepository.findAllByProfileId(profile.id())
      .stream()
      .filter(not(Page::isDeleted))
      .filter(Page::isHome)
      .findFirst()
      .map(pageConverter::convert)
      .orElseThrow();

    return new PublicProfileModel(pageModel);
  }
}
