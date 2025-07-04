package dev.abarmin.aml.dashboard.block.header;

import dev.abarmin.aml.dashboard.domain.BlockProps;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeaderBlockProps implements BlockProps {
  @NotEmpty
  private String text;

  @NotNull
  private HeaderLevel level;

  @NotNull
  private TextAlignment alignment;
}
