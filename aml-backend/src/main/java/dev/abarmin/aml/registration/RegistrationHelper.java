package dev.abarmin.aml.registration;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationHelper {

  public static boolean isOAuth2User(final @Nullable Authentication authentication) {
    if (authentication == null) {
      return false;
    }
    return authentication.getPrincipal() instanceof DefaultOidcUser;
  }

}
