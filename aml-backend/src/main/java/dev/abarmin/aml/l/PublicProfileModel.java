package dev.abarmin.aml.l;

import dev.abarmin.aml.dashboard.model.DashboardProfile;
import dev.abarmin.aml.dashboard.model.PageModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PublicProfileModel {
  private final PageModel currentPage;
  private final DashboardProfile profile;
}
