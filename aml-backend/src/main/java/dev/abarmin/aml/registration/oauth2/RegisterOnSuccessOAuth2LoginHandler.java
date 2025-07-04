package dev.abarmin.aml.registration.oauth2;

import dev.abarmin.aml.registration.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;

@Component
@RequiredArgsConstructor
public class RegisterOnSuccessOAuth2LoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  private final UserRepository userRepository;

    @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
    if (isRegisteredUser(authentication)) {
      // the user is known to the system, just forward to the requested page
      super.onAuthenticationSuccess(request, response, authentication);
      return;
    }
    // it's a new user, need to go through the registration process
    getRedirectStrategy().sendRedirect(request, response, "/register/oidc");
  }

  private boolean isRegisteredUser(final @NonNull Authentication authentication) {
    checkArgument(
      authentication instanceof OAuth2AuthenticationToken,
      "Invalid authentication type");

    final OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

    checkArgument(
      StringUtils.equalsIgnoreCase(token.getAuthorizedClientRegistrationId(), "google"),
      "Only Google authentication is supported"
    );

    final OAuth2User principal = token.getPrincipal();
    final String email = principal.getAttribute("email");

    checkArgument(
      StringUtils.isNoneEmpty(email),
      "Email attribute is empty"
    );

    return userRepository.findByEmail(email).isPresent();
  }
}
