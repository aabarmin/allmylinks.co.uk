package dev.abarmin.aml.dashboard.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlockType {
  AVATAR_BLOCK("Avatar", "bi-fonts"),
  HEADER_BLOCK("Header", "bi-fonts");

  private final String name;
  private final String icon;
}
