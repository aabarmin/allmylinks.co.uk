package dev.abarmin.aml.mail.extractor;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.profile.domain.ProfileChangeRequest;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProfileChangeRequestForBackofficeExtractor implements MailParamsExtractor<ProfileChangeRequest> {
  private final AppConfiguration configuration;
  private final UserRepository userRepository;
  private final ObjectMapper objectMapper;

  @Override
  @SneakyThrows
  public MailParams apply(ProfileChangeRequest profileChangeRequest) {
    final String adminEmail = configuration.getBackoffice().getAdminEmail();
    final User user = userRepository.findById(profileChangeRequest.userId()).orElseThrow();

    return MailParams.of(
      adminEmail,
      Map.of(
        "baseUrl", configuration.getBaseUrl(), // todo, extract to some basic class
        "loginLink", configuration.getBaseUrl() + "/login",
        "emailTo", configuration.getBackoffice().getAdminEmail(),

        "requestType", profileChangeRequest.changeType().name(),
        "requestContent", objectMapper.writeValueAsString(profileChangeRequest),
        "userEmail", user.email()
      )
    );
  }
}
