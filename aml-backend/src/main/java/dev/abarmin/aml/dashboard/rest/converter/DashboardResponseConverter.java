package dev.abarmin.aml.dashboard.rest.converter;

import dev.abarmin.aml.dashboard.converter.BlockTypeConverter;
import dev.abarmin.aml.dashboard.converter.DashboardProfileConverter;
import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.rest.response.DashboardResponse;
import dev.abarmin.aml.registration.domain.Profile;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DashboardResponseConverter {

  private final DashboardProfileConverter profileConverter;
  private final BlockTypeConverter blockTypeConverter;
  private final PageResponseConverter pageConverter;

  public DashboardResponse convert(@NonNull Profile profile,
                                   @NonNull Page page) {
    return new DashboardResponse(
      profileConverter.convert(profile),
      blockTypeConverter.convert(Arrays.asList(BlockType.values())),
      pageConverter.convert(page)
    );
  }
}
