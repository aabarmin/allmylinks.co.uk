package dev.abarmin.aml.dashboard.block.social;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialNetworkLink {
  @NotNull
  @Builder.Default
  private SocialNetworkIcon network = SocialNetworkIcon.FACEBOOK;

  @URL
  @NotEmpty
  private String url;
}
