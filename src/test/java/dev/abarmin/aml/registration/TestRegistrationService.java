package dev.abarmin.aml.registration;

import dev.abarmin.aml.utils.RandomUtils;
import dev.abarmin.aml.registration.domain.AccountType;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import dev.abarmin.aml.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
public class TestRegistrationService {
  private final RegistrationService registrationService;
  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;

  public TestCredentials getRegisteredUser() {
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

    return new TestCredentials(email, password);
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
