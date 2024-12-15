package dev.abarmin.aml.dashboard.block.button;

import dev.abarmin.aml.dashboard.domain.BlockProps;
import dev.abarmin.aml.dashboard.domain.BlockType;
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
public class LinkButtonBlockProps implements BlockProps {
  public static final String DEFAULT_TEXT = "All my links page";

  @NotEmpty
  private String text;

  @URL
  @NotEmpty
  private String link;

  @NotNull
  @Builder.Default
  private LinkButtonSize size = LinkButtonSize.LARGE;

  @Override
  public BlockType type() {
    return BlockType.BUTTON_BLOCK;
  }
}
