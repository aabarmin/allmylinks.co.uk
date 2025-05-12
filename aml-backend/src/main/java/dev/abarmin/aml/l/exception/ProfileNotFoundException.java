package dev.abarmin.aml.l.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileNotFoundException extends RuntimeException {
  private final String profileLink;
}
