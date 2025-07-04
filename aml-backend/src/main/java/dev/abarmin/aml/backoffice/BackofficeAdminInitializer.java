package dev.abarmin.aml.backoffice;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BackofficeAdminInitializer {
  private final BackofficeAccessService accessService;
  private final AppConfiguration configuration;
  private final ProfileRepository profileRepository;

  @Bean
  public ApplicationRunner backofficeInitializer() {
    return args -> {
      final String adminProfile = configuration.getBackoffice().getDefaults().getAdminProfile();
      final Optional<Profile> profileOptional = profileRepository.findByLink(adminProfile);
      if (profileOptional.isEmpty()) {
        log.warn("Profile with link {} not found", adminProfile);
        return;
      }
      final Profile profile = profileOptional.get();
      accessService.grantBackofficeAccess(profile);
    };
  }
}
