package dev.abarmin.aml.dashboard.model;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class DashboardModel {
  private DashboardProfile profile;
  private Collection<BlockTypeModel> availableBlocks;
  private PageModel currentPage;
  private @Nullable BlockModel currentBlock;
}
