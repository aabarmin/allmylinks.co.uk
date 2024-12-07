package dev.abarmin.aml.mail.extractor;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RegistrationDoneExtractor implements MailParamsExtractor<User> {
  private final AppConfiguration configuration;
  private final ProfileRepository profileRepository;

  @Override
  public MailParams apply(User user) {
    final Profile profile = profileRepository.findByUserId(user.id()).orElseThrow();

    return new MailParams(user.email(), Map.of(
      "name", user.userName(),
      "email", user.email(),
      "baseUrl", configuration.getBaseUrl(),
      "loginLink", configuration.getBaseUrl() + "/login",
      "profileLink", configuration.getBaseUrl() + "/l/" + profile.link()
    ));
  }
}
