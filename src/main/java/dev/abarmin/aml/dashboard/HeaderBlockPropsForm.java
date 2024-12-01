package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.domain.HeaderBlockProps;
import dev.abarmin.aml.dashboard.model.HeaderLevel;
import dev.abarmin.aml.dashboard.model.TextAlignment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

@Data
public class HeaderBlockPropsForm {
  @NotEmpty
  private String text;
  @NotNull
  private HeaderLevel level;
  @NotNull
  private TextAlignment alignment;

  public HeaderBlockProps toProps() {
    return new HeaderBlockProps(text, level, alignment);
  }
}
