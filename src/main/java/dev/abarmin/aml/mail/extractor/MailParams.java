package dev.abarmin.aml.mail.extractor;

import java.util.Map;

public record MailParams(
  String to,
  Map<String, Object> templateParams
) {
  public static MailParams of(String to, Map<String, Object> templateParams) {
    return new MailParams(to, templateParams);
  }

  public static MailParams empty() {
    return new MailParams(null, Map.of());
  }
}
