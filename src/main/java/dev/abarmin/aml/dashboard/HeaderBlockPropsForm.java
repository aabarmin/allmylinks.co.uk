package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.domain.HeaderBlockProps;
import dev.abarmin.aml.dashboard.model.HeaderLevel;
import dev.abarmin.aml.dashboard.model.TextAlignment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@Data
public class HeaderBlockPropsForm {
  private B currentBlock;

  @Data
  public static class Props {
    private String text;
  }

  @Data
  public static class B {
    private Props blockProps;
  }

  public HeaderBlockProps toProps() {
    throw new UnsupportedOperationException("Not implemented");
//    return new HeaderBlockProps(text, level, alignment);
  }
}
