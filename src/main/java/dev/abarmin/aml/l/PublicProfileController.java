package dev.abarmin.aml.l;

import dev.abarmin.aml.l.exception.ProfileNotFoundException;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class PublicProfileController {
  private final ProfileRepository profileRepository;
  private final PublicProfileConverter profileConverter;

  @ModelAttribute("model")
  public PublicProfileModel profileModel(@PathVariable("profileLink") String profileLink) {
    final Profile profile = profileRepository.findByLink(profileLink)
      .orElseThrow(() -> new ProfileNotFoundException(profileLink));
    
    return profileConverter.convert(profile);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ProfileNotFoundException.class)
  public String profileNotFound(ProfileNotFoundException e, Model model) {
    model.addAttribute("profileLink", e.getProfileLink());
    return "l/error-404";
  }

  @GetMapping("/l/{profileLink}")
  public String getPublicProfile() {
    return "l/l";
  }
}
