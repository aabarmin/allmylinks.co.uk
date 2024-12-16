package dev.abarmin.aml.dashboard;

import com.google.common.base.Joiner;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CssHelper {
  public String toStyle(@NonNull Map<String, String> style) {
    return Joiner.on("; ")
      .withKeyValueSeparator(": ")
      .join(style);
  }
}
