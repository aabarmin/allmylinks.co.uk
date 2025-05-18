package dev.abarmin.aml.dashboard.domain;

import dev.abarmin.aml.dashboard.block.header.HeaderLevel;
import dev.abarmin.aml.dashboard.block.header.TextAlignment;
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
public final class HeaderBlockProps implements BlockProps {
  @NotEmpty
  private String text;

  @NotNull
  private HeaderLevel level;

  @NotNull
  private TextAlignment alignment;
}
