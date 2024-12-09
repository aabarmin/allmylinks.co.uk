package dev.abarmin.aml.dashboard.block.social;

import dev.abarmin.aml.dashboard.domain.BlockProps;
import dev.abarmin.aml.dashboard.domain.BlockType;
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
public class SocialNetworksBlockProps implements BlockProps {
  @Valid
  @Builder.Default
  private List<SocialNetworkLink> links = new ArrayList<>();

  @Override
  public BlockType type() {
    return BlockType.SOCIAL_NETWORKS_BLOCK;
  }
}
