package dev.abarmin.aml.dashboard.block.header;

import dev.abarmin.aml.dashboard.block.BlockPropsSupport;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HeaderBlockPropsForm implements BlockPropsSupport<HeaderBlockProps> {
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
    private String text;
    private HeaderLevel level;
    private TextAlignment alignment;
  }

  @Override
  public HeaderBlockProps toProps() {
    return new HeaderBlockProps(
      currentBlock.getBlockProps().getText(),
      currentBlock.getBlockProps().getLevel(),
      currentBlock.getBlockProps().getAlignment()
    );
  }
}
