package dev.abarmin.aml.dashboard.block.button;

import dev.abarmin.aml.dashboard.domain.BlockProps;
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
public final class LinkButtonBlockProps implements BlockProps {
  public static final String DEFAULT_TEXT = "All my links page";

  @NotEmpty
  private String text;

  @URL
  @NotEmpty
  private String link;

  @NotNull
  @Builder.Default
  private LinkButtonSize size = LinkButtonSize.LARGE;

  @NotNull
  @Builder.Default
  private LinkButtonColor color = LinkButtonColor.BLUE;
}
