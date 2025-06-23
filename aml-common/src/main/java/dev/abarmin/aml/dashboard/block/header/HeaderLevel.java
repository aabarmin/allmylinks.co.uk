package dev.abarmin.aml.dashboard.block.header;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HeaderLevel {
  H1("h1"),
  H2("h2"),
  H3("h3");

  private final String htmlTag;
}
