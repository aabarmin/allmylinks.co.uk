package dev.abarmin.aml.mail.template;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.mail.extractor.backoffice.BackofficeProfileChangeRequestExtractor;
import dev.abarmin.aml.mail.extractor.RegistrationDoneExtractor;
import dev.abarmin.aml.mail.extractor.backoffice.BackofficeRegistrationDoneExtractor;
import dev.abarmin.aml.mail.extractor.backoffice.BackofficeSubscriptionExtractor;
import dev.abarmin.aml.profile.domain.ProfileChangeRequest;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.subscribe.Subscription;
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
      "\uD83D\uDE4C Welcome to AllMyLinks!",
      "mail/registration-done",
      context.getBean(RegistrationDoneExtractor.class)
    );
  }

  public MailTemplate<User> registrationDoneAdmin() {
    final AppConfiguration.MailService mailService = appConfiguration.getMailService();

    return new MailTemplate<>(
      mailService.getFromEmail(),
      mailService.getFromName(),
      "[AML] New user registered",
      "mail/registration-done-admin",
      context.getBean(BackofficeRegistrationDoneExtractor.class)
    );
  }

  public MailTemplate<ProfileChangeRequest> profileChangeRequestCreated() {
    final AppConfiguration.MailService mailService = appConfiguration.getMailService();

    return new MailTemplate<>(
      mailService.getFromEmail(),
      mailService.getFromName(),
      "[AML] Profile change request created",
      "mail/profile-change-request-created",
      context.getBean(BackofficeProfileChangeRequestExtractor.class)
    );
  }

  public MailTemplate<Subscription> subscriptionCreated() {
    final AppConfiguration.MailService mailService = appConfiguration.getMailService();

    return new MailTemplate<>(
      mailService.getFromEmail(),
      mailService.getFromName(),
      "[AML] New subscription created",
      "mail/subscription-created",
      context.getBean(BackofficeSubscriptionExtractor.class)
    );
  }
}
