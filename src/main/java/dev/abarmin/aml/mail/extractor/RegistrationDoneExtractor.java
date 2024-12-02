package dev.abarmin.aml.mail.extractor;

import dev.abarmin.aml.mail.MailParams;
import dev.abarmin.aml.mail.MailParamsExtractor;
import dev.abarmin.aml.registration.domain.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RegistrationDoneExtractor implements MailParamsExtractor<User> {
  @Override
  public MailParams apply(User user) {
    return new MailParams(user.email(), Map.of());
  }
}
