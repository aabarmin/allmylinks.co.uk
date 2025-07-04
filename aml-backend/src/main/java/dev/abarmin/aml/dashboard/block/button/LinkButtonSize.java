package dev.abarmin.aml.dashboard.block.button;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LinkButtonSize {
  NORMAL("Normal", ""),
  SMALL("Small", "btn-sm"),
  LARGE("Large", "btn-lg");

  private final String name;
  private final String htmlClass;
}
