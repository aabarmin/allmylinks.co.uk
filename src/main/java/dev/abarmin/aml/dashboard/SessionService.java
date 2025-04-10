package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.login.CustomUserDetails;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import dev.abarmin.aml.registration.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
public class SessionService {
  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;

  public User getUser(@NonNull Authentication authentication) {
    checkArgument(authentication.isAuthenticated(), "Not authenticated");

    final Object principalCandidate = authentication.getPrincipal();
    final String email;

    if (principalCandidate instanceof CustomUserDetails details) {
      email = details.username();
    } else if (principalCandidate instanceof org.springframework.security.core.userdetails.User securityUser) {
      email = securityUser.getUsername();
    } else if (principalCandidate instanceof DefaultOidcUser oidcUser) {
      email = oidcUser.getEmail();
    } else {
      throw new IllegalArgumentException("Unsupported principal type " + principalCandidate.getClass());
    }
    return userRepository.findByEmail(email)
      .orElseThrow(() -> new IllegalStateException("User not found"));
  }

  public Profile getProfile(@NonNull Authentication authentication) {
    final User user = getUser(authentication);

    final Profile profile = profileRepository.findByUserId(user.id())
      .orElseThrow(() -> new IllegalStateException("Profile not found"));

    checkArgument(profile != null, "Profile is required");

    return profile;
  }
}
