package dev.abarmin.aml.dashboard.block.social;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialNetworkLink {
  @Builder.Default
  private SocialNetworkIcon network = SocialNetworkIcon.FACEBOOK;
  private String url;
}
