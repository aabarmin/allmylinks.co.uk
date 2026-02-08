package dev.abarmin.aml.backoffice.dashboard.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BackofficeProfileChangeRequestStats {
  private long total;
  private long waiting;
}
