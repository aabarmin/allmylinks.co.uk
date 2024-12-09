package dev.abarmin.aml.dashboard.block.link;

import dev.abarmin.aml.dashboard.block.BlockPropsSupport;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LinkButtonBlockPropsForm implements BlockPropsSupport<LinkButtonBlockProps> {
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
    @NotEmpty
    private String text;

    @NotEmpty
    private String link;
  }

  @Override
  public LinkButtonBlockProps toProps() {
    return LinkButtonBlockProps.builder()
      .link(currentBlock.blockProps.link)
      .text(currentBlock.blockProps.text)
      .build();
  }
}
