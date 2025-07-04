package dev.abarmin.aml.dashboard.converter;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.dashboard.model.ProfileShare;
import dev.abarmin.aml.registration.domain.Profile;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShareConverter {
  private final AppConfiguration configuration;

  public ProfileShare convert(final @NonNull Profile profile) {
    return ProfileShare.builder()
      .onTwitter(onTwitter(profile))
      .onFacebook(onFacebook(profile))
      .onLinkedin(onLinkedIn(profile))
      .build();
  }

  private String onFacebook(Profile profile) {
    String template = "https://www.facebook.com/sharer/sharer.php?u=%s";
    return String.format(template, getProfileUrl(profile));
  }

  private String onTwitter(Profile profile) {
    String template = "https://twitter.com/intent/tweet?text=%s&url=%s";
    return String.format(template, "That's All My Links in one place!", getProfileUrl(profile));
  }

  private String onLinkedIn(Profile profile) {
    String template = "https://www.linkedin.com/shareArticle?mini=true&url=%s&title=%s&summary=YOUR_SUMMARY&source=YOUR_SOURCE";
    return String.format(template, getProfileUrl(profile), "All My Links", "All My Links in one place!", getProfileUrl(profile));
  }

  private String getProfileUrl(Profile profile) {
    return configuration.getBaseUrl() + "/l/" + profile.link();
  }
}
