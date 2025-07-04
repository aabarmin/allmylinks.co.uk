package dev.abarmin.aml.dashboard.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlockType {
  AVATAR_BLOCK(
    "Avatar",
    "bi-person-circle",
    "block-avatar",
    "block-avatar-props"
  ),
  BUTTON_BLOCK(
    "Link button",
    "bi-link",
    "block-button",
    "block-button-props"
  ),
  HEADER_BLOCK(
    "Header",
    "bi-fonts",
    "block-header",
    "block-header-props"
  ),
  SOCIAL_NETWORKS_BLOCK(
    "Social networks",
    "bi-hand-thumbs-up",
    "block-social-networks",
    "block-social-networks-props"
  );

  private final String name;
  private final String icon;
  private final String previewComponent;
  private final String configComponent;
}
