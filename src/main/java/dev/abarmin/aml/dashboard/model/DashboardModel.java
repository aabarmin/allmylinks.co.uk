package dev.abarmin.aml.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class DashboardModel {
  private DashboardProfile profile;
  private Collection<BlockTypeModel> availableBlocks;
  private PageModel currentPage;
}
