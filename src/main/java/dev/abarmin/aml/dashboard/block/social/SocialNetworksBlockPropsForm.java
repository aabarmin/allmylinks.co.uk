package dev.abarmin.aml.dashboard.block.social;

import dev.abarmin.aml.dashboard.block.BlockPropsSupport;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SocialNetworksBlockPropsForm implements BlockPropsSupport<SocialNetworksBlockProps> {
  private CurrentBlock currentBlock = new CurrentBlock();

  @Data
  public static class CurrentBlock {
    @NotNull
    @Valid
    private BlockProps blockProps = new BlockProps();
  }

  @Data
  public static class BlockProps {
    private List<SocialNetworkLink> links = new ArrayList<>();
  }


  @Override
  public SocialNetworksBlockProps toProps() {
    return SocialNetworksBlockProps.builder()
      .links(currentBlock.getBlockProps().getLinks())
      .build();
  }
}
