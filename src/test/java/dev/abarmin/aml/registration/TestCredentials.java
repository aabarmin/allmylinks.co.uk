package dev.abarmin.aml.registration;

public record TestCredentials(
  String username,
  String password,
  long userId,
  long currentProfileId
) {
}
