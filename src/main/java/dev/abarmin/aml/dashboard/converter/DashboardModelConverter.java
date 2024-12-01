package dev.abarmin.aml.dashboard.converter;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.model.BlockModel;
import dev.abarmin.aml.dashboard.model.DashboardModel;
import dev.abarmin.aml.dashboard.model.DashboardProfile;
import dev.abarmin.aml.registration.domain.Profile;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DashboardModelConverter {
  private final AppConfiguration configuration;
  private final BlockTypeConverter blockTypeConverter;
  private final PageConverter pageConverter;
  private final BlockConverter blockConverter;

  public DashboardModel convert(@NonNull Profile profile,
                                @NonNull Page page,
                                @Nullable Block block) {
    final DashboardModel model = new DashboardModel(
      convert(profile),
      blockTypeConverter.convert(Arrays.asList(BlockType.values())),
      pageConverter.convert(page),
      convert(block)
    );

    log.info("Generated model {}", model);

    return model;
  }

  private BlockModel convert(@Nullable Block block) {
    return Optional.ofNullable(block)
      .map(blockConverter::convert)
      .orElse(null);
  }

  private DashboardProfile convert(final Profile profile) {
    return new DashboardProfile(
      configuration.getBaseUrl() + "/l/" + profile.link()
    );
  }
}
