package dev.abarmin.aml.mail;

import dev.abarmin.aml.mail.template.MailTemplate;
import lombok.NonNull;

public interface MailService {

  <T> MailSendResult send(@NonNull MailTemplate<T> template, @NonNull T source);

}
