package dev.abarmin.aml.backoffice.dashboard;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BackofficeTaskStats {
  private long waiting;
  private long failed;
  private long total;
}
