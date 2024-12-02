package dev.abarmin.aml.mail;

import java.util.Map;

public record MailParams(
  String to,
  Map<String, Object> templateParams
) {
  public static MailParams empty() {
    return new MailParams(null, Map.of());
  }
}
