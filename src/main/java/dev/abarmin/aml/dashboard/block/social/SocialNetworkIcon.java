package dev.abarmin.aml.dashboard.block.social;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SocialNetworkIcon {
  FACEBOOK("bi-facebook"),
  TWITTER("bi-twitter"),
  X("bi-twitter-x"),
  INSTAGRAM("bi-instagram");

  private final String htmlClass;
}
