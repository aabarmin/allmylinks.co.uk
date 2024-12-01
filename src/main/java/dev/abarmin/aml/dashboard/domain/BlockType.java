package dev.abarmin.aml.dashboard.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlockType {
  HEADER_BLOCK("Header", "bi-fonts");

  private final String name;
  private final String icon;
}
