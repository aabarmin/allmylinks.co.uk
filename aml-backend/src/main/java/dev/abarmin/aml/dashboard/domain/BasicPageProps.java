package dev.abarmin.aml.dashboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class BasicPageProps implements PageProps {
  private String backgroundColor = "#ffffff";

  @JsonIgnore
  public Map<String, String> getPageStyle() {
    return Map.of(
      "min-height", "954px",
      "max-width", "540px",
      "background-color", backgroundColor
    );
  }
}
