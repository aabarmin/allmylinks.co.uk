package dev.abarmin.aml.backoffice.users;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileHelper {
  private final AppConfiguration configuration;
  private final ProfileRepository  profileRepository;

  public String getProfileLink(User user) {
    return profileRepository.findByUserId(user.id())
      .map(profile -> configuration.getBaseUrl() + "/l/" + profile.link())
      .orElse("Unknown");
  }
}
