package dev.abarmin.aml.dashboard.block.header;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TextAlignment {
  LEFT("text-start"),
  CENTER("text-center"),
  RIGHT("text-end");

  private final String htmlClass;
}
