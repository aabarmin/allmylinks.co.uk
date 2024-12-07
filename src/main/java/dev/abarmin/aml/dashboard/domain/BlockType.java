package dev.abarmin.aml.dashboard.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlockType {
  AVATAR_BLOCK("Avatar", "bi-person-circle"),
  BUTTON_BLOCK("Link button", "bi-link"),
  HEADER_BLOCK("Header", "bi-fonts"),
  SOCIAL_NETWORKS_BLOCK("Social networks", "bi-hand-thumbs-up");

  private final String name;
  private final String icon;
}
