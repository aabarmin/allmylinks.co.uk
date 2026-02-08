package dev.abarmin.aml.backoffice.dashboard;

import dev.abarmin.aml.backoffice.dashboard.model.BackofficeProfileChangeRequestStats;
import dev.abarmin.aml.backoffice.dashboard.model.BackofficeTaskStats;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BackofficeDashboard {
  private long totalUsers;
  private BackofficeTaskStats taskStats;
  private BackofficeProfileChangeRequestStats changeRequestStats;
}
