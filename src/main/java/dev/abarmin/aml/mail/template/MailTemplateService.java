package dev.abarmin.aml.mail.template;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.mail.extractor.RegistrationDoneExtractor;
import dev.abarmin.aml.registration.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailTemplateService {
  private final AppConfiguration appConfiguration;
  private final ApplicationContext context;

  public MailTemplate<User> registrationDone() {
    final AppConfiguration.MailService mailService = appConfiguration.getMailService();

    return new MailTemplate<>(
      mailService.getFromEmail(),
      mailService.getFromName(),
      "Welcome and thank you for the registration!",
      "mail/registration-done",
      context.getBean(RegistrationDoneExtractor.class)
    );
  }
}
