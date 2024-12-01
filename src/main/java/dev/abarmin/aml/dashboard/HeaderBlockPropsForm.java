package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.domain.HeaderBlockProps;
import dev.abarmin.aml.dashboard.model.HeaderLevel;
import dev.abarmin.aml.dashboard.model.TextAlignment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HeaderBlockPropsForm {
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

  public HeaderBlockProps toProps() {
    return new HeaderBlockProps(
      currentBlock.getBlockProps().getText(),
      currentBlock.getBlockProps().getLevel(),
      currentBlock.getBlockProps().getAlignment()
    );
  }
}
