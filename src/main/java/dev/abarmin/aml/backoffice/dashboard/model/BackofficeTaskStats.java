package dev.abarmin.aml.backoffice.dashboard.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BackofficeTaskStats {
  private long waiting;
  private long failed;
  private long total;
}
