package dev.abarmin.aml.l;

import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PublicProfileController {
  private final ProfileRepository profileRepository;
  private final PublicProfileConverter profileConverter;

  @ModelAttribute("model")
  public PublicProfileModel profileModel(@PathVariable("profileLink") String profileLink) {
    final Profile profile = profileRepository.findByLink(profileLink)
      .orElseThrow(() -> new IllegalArgumentException("Profile not found"));

    return profileConverter.convert(profile);
  }

  @GetMapping("/l/{profileLink}")
  public String getPublicProfile() {
    return "l/l";
  }
}
