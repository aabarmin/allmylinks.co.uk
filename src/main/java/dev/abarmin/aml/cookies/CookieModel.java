package dev.abarmin.aml.cookies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CookieModel {
  @Builder.Default
  private boolean accepted = false;
}
