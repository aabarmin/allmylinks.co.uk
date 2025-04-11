package dev.abarmin.aml.dashboard.converter;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.dashboard.model.DashboardProfile;
import dev.abarmin.aml.registration.domain.Profile;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DashboardProfileConverter {
  private final AppConfiguration configuration;
  private final ShareConverter shareConverter;

  public DashboardProfile convert(final @NonNull Profile profile) {
    return new DashboardProfile(
      configuration.getBaseUrl() + "/l/" + profile.link(),
      profile.link(),
      profile.qrCode(),
      shareConverter.convert(profile)
    );
  }
}
