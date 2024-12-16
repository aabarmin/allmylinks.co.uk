package dev.abarmin.aml.dashboard.block.button;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LinkButtonColor {
  BLUE("Blue", "btn-primary"),
  GRAY("Gray", "btn-secondary"),
  GREEN("Green", "btn-success"),
  RED("Red", "btn-danger"),
  YELLOW("Yellow", "btn-warning"),
  LIGHT_BLUE("Light blue", "btn-info"),
  LIGHT_GREY("Light grey", "btn-light"),
  DARK("Dark", "btn-dark"),
  REGULAR("Regular link", "btn-link");

  private final String name;
  private final String htmlClass;
}
