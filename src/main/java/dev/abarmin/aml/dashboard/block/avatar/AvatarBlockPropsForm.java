package dev.abarmin.aml.dashboard.block.avatar;

import dev.abarmin.aml.dashboard.block.BlockPropsSupport;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AvatarBlockPropsForm implements BlockPropsSupport<AvatarBlockProps> {
  @Valid
  @NotNull
  private CurrentBlock currentBlock;

  @Data
  public static class CurrentBlock {
    @NotNull
    @Valid
    private BlockProps blockProps;
  }

  @Data
  public static class BlockProps {
    private String imageUrl;
  }

  @Override
  public AvatarBlockProps toProps() {
    return AvatarBlockProps.builder()
      .imageUrl(getCurrentBlock().getBlockProps().getImageUrl())
      .build();
  }
}
