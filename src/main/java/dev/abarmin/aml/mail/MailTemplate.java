package dev.abarmin.aml.mail;

public record MailTemplate<T>(
  String fromEmail,
  String fromName,
  String subject,
  String templateName,
  MailParamsExtractor<T> extractor
) {
}
