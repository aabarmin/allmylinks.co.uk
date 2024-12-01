package dev.abarmin.aml.dashboard.model;

import jakarta.annotation.Nullable;

import java.util.Collection;

public record DashboardModel(
  DashboardProfile profile,
  Collection<BlockTypeModel> availableBlocks,
  PageModel currentPage,
  @Nullable BlockModel currentBlock
) {
}
