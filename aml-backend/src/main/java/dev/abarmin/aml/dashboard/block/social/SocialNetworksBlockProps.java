package dev.abarmin.aml.dashboard.block.social;

import dev.abarmin.aml.dashboard.domain.BlockProps;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class SocialNetworksBlockProps implements BlockProps {
  @Valid
  @Builder.Default
  private List<SocialNetworkLink> links = new ArrayList<>();

}
