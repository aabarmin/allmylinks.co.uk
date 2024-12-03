package dev.abarmin.aml.dashboard.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlockType {
  AVATAR_BLOCK("Avatar", "bi-fonts"),
  BUTTON_BLOCK("Link button", "bi-fonts"),
  HEADER_BLOCK("Header", "bi-fonts"),
  SOCIAL_NETWORKS_BLOCK("Social networks", "bi-fonts"),;

  private final String name;
  private final String icon;
}
