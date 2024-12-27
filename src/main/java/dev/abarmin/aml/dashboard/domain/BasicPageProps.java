package dev.abarmin.aml.dashboard.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicPageProps implements PageProps {
  private String backgroundColor = "#ffffff";

  @JsonIgnore
  public Map<String, String> getPageStyle() {
    return Map.of(
      "background-color", backgroundColor
    );
  }
}
