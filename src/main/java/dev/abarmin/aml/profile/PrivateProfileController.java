package dev.abarmin.aml.profile;

import dev.abarmin.aml.dashboard.SessionService;
import dev.abarmin.aml.profile.converter.ProfileConverter;
import dev.abarmin.aml.profile.domain.ChangeEmailRequest;
import dev.abarmin.aml.profile.domain.ProfileChangeRequest;
import dev.abarmin.aml.profile.domain.ProfileChangeStatus;
import dev.abarmin.aml.profile.domain.ProfileChangeType;
import dev.abarmin.aml.profile.model.ProfileModel;
import dev.abarmin.aml.registration.domain.Profile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PrivateProfileController {
  private final SessionService sessionService;
  private final ProfileConverter profileConverter;
  private final ProfileChangeRepository changeRepository;

  @ModelAttribute("profile")
  public ProfileModel profile(Authentication authentication) {
    final Profile profile = sessionService.getProfile(authentication);
    return profileConverter.convert(profile);
  }

  @GetMapping("/private/profile")
  public String profileHome() {
    return "private/profile";
  }

  @PostMapping("/private/profile/email")
  public String changeEmail(Authentication authentication,
                            @Valid ChangeEmailRequest request,
                            BindingResult bindingResult) {

    if (!bindingResult.hasErrors()) {
      final Profile profile = sessionService.getProfile(authentication);
      changeRepository.save(new ProfileChangeRequest(
        profile,
        ProfileChangeType.CHANGE_EMAIL,
        request
      ));
    }

    return "redirect:/private/profile";
  }

  @GetMapping("/private/profile/email/cancel")
  public String cancelChangeEmail(Authentication authentication) {
    final Profile profile = sessionService.getProfile(authentication);
    changeRepository.findByProfileIdAndChangeTypeAndChangeStatus(
      profile.id(), ProfileChangeType.CHANGE_EMAIL, ProfileChangeStatus.CREATED
    )
      .map(ProfileChangeRequest::cancel)
      .ifPresent(changeRepository::save);

    return "redirect:/private/profile";
  }
}
