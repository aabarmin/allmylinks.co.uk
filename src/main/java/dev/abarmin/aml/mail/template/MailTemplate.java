package dev.abarmin.aml.mail.template;

import dev.abarmin.aml.mail.extractor.MailParamsExtractor;

public record MailTemplate<T>(
  String fromEmail,
  String fromName,
  String subject,
  String templateName,
  MailParamsExtractor<T> extractor
) {
}
