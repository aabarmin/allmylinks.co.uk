package dev.abarmin.aml.dashboard.model;

import java.util.Collection;

public record DashboardModel(
  DashboardProfile profile,
  Collection<BlockTypeModel> availableBlocks,
  PageModel currentPage
) {
}
