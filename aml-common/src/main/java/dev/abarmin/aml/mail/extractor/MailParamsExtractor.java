package dev.abarmin.aml.mail.extractor;

import java.util.function.Function;

public interface MailParamsExtractor<T> extends Function<T, MailParams> {
  static <T> MailParamsExtractor<T> noop() {
    return o -> MailParams.empty();
  }
}
