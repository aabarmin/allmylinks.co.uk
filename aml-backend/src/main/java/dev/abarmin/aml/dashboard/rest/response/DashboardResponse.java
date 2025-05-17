package dev.abarmin.aml.dashboard.rest.response;

import dev.abarmin.aml.dashboard.model.BlockTypeModel;
import dev.abarmin.aml.dashboard.model.DashboardProfile;

import java.util.Collection;

public record DashboardResponse(
  DashboardProfile profile,
  Collection<BlockTypeModel> availableBlocks,
  PageResponse currentPage
) {
}
