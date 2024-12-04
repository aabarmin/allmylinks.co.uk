package dev.abarmin.aml.registration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import dev.abarmin.aml.registration.domain.AccountType;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import dev.abarmin.aml.registration.repository.UserRepository;
import dev.abarmin.aml.utils.RandomUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
public class TestRegistrationService {
  private static final String REGISTERED_USER_KEY = "registered_user";

  private final RegistrationService registrationService;
  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;

  private final Cache<String, TestCredentials> userCache = CacheBuilder.newBuilder()
    .expireAfterAccess(Duration.ofMinutes(5))
    .build();

  @SneakyThrows
  public TestCredentials getRegisteredUser() {
    return userCache.get(REGISTERED_USER_KEY, this::createTestUser);
  }

  private TestCredentials createTestUser() {
    final String email = getNextEmail();
    final String password = RandomStringUtils.secure().nextAlphanumeric(8);
    final String link = getNextProfile();

    final RegistrationForm form = RegistrationForm.builder()
      .link(link)
      .name("Test User")
      .email(email)
      .password(password)
      .acceptTerms(true)
      .build();

    final User registered = registrationService.register(form, AccountType.USERNAME_PASSWORD);
    checkArgument(registered != null, "User wasn't registered");

    final Profile profile = profileRepository.findByUserId(registered.id()).orElseThrow();

    return new TestCredentials(email, password, registered.id(), profile.id());
  }

  private String getNextEmail() {
    String email = RandomUtils.email();
    while (userRepository.findByEmail(email).isPresent()) {
      email = RandomUtils.email();
    }
    return email;
  }

  private String getNextProfile() {
    String link = RandomStringUtils.secure().nextAlphanumeric(10).toLowerCase();
    while (profileRepository.findByLink(link).isPresent()) {
      link = RandomStringUtils.secure().nextAlphanumeric(10).toLowerCase();
    }
    return link;
  }
}
