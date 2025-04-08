package dev.abarmin.aml.mail.extractor.backoffice;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.mail.extractor.MailParams;
import dev.abarmin.aml.mail.extractor.MailParamsExtractor;
import dev.abarmin.aml.subscribe.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BackofficeSubscriptionExtractor implements MailParamsExtractor<Subscription> {
  private final AppConfiguration configuration;

  @Override
  public MailParams apply(Subscription subscription) {
    throw new UnsupportedOperationException();
//    final String adminEmail = configuration.getBackoffice().getAdminEmail();
//
//    return MailParams.of(
//      configuration.getBackoffice().getAdminEmail(),
//      Map.of(
//        "baseUrl", configuration.getBaseUrl(), // todo, extract to some basic class
//        "loginLink", configuration.getBaseUrl() + "/login",
//        "emailTo", configuration.getBackoffice().getAdminEmail(),
//
//        "userEmail", subscription.email()
//      )
//    );
  }
}
