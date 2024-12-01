package dev.abarmin.aml.dashboard.converter;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.model.DashboardModel;
import dev.abarmin.aml.dashboard.model.DashboardProfile;
import dev.abarmin.aml.registration.domain.Profile;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class DashboardModelConverter {
  private final AppConfiguration configuration;
  private final BlockTypeConverter blockTypeConverter;
  private final PageConverter pageConverter;

  public DashboardModel convert(@NonNull Profile profile, @NonNull Page page) {
    final DashboardModel model = new DashboardModel(
      convert(profile),
      blockTypeConverter.convert(Arrays.asList(BlockType.values())),
      pageConverter.convert(page)
    );

    log.info("Generated model {}", model);

    return model;
  }

  private DashboardProfile convert(final Profile profile) {
    return new DashboardProfile(
      configuration.getBaseUrl() + "/l/" + profile.link()
    );
  }
}
