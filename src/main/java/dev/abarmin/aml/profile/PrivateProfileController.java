package dev.abarmin.aml.profile;

import dev.abarmin.aml.dashboard.SessionService;
import dev.abarmin.aml.mail.MailSendResult;
import dev.abarmin.aml.mail.MailService;
import dev.abarmin.aml.mail.template.MailTemplate;
import dev.abarmin.aml.mail.template.MailTemplateService;
import dev.abarmin.aml.profile.converter.ProfileConverter;
import dev.abarmin.aml.profile.domain.ChangeEmailRequest;
import dev.abarmin.aml.profile.domain.ChangeLinkRequest;
import dev.abarmin.aml.profile.domain.DeactivateProfileRequest;
import dev.abarmin.aml.profile.domain.ProfileChangeRequest;
import dev.abarmin.aml.profile.domain.ProfileChangeStatus;
import dev.abarmin.aml.profile.domain.ProfileChangeType;
import dev.abarmin.aml.profile.model.ProfileModel;
import dev.abarmin.aml.registration.domain.Profile;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PrivateProfileController {
  private final SessionService sessionService;
  private final ProfileConverter profileConverter;
  private final ProfileChangeRepository changeRepository;
  private final MailService mailService;
  private final MailTemplateService templateService;

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
      final ProfileChangeRequest savedRequest = changeRepository.save(new ProfileChangeRequest(
        profile,
        ProfileChangeType.CHANGE_EMAIL,
        request
      ));
      notifyAdminAboutNewChangeRequest(savedRequest);
    }

    return "redirect:/private/profile";
  }


  @PostMapping("/private/profile/link")
  public String changeLink(Authentication authentication,
                           @Valid ChangeLinkRequest request,
                           BindingResult bindingResult) {

    if (!bindingResult.hasErrors()) {
      final Profile profile = sessionService.getProfile(authentication);
      final ProfileChangeRequest savedRequest = changeRepository.save(new ProfileChangeRequest(
        profile,
        ProfileChangeType.CHANGE_LINK,
        request
      ));
      notifyAdminAboutNewChangeRequest(savedRequest);
    }

    return "redirect:/private/profile";
  }

  @PostMapping("/private/profile/deactivate")
  public String profileDeactivate(Authentication authentication,
                                  @Valid DeactivateProfileRequest request,
                                  BindingResult bindingResult) {

    if (!bindingResult.hasErrors()) {
      final Profile profile = sessionService.getProfile(authentication);
      final ProfileChangeRequest savedRequest = changeRepository.save(new ProfileChangeRequest(
        profile,
        ProfileChangeType.PROFILE_DEACTIVATE,
        request
      ));
      notifyAdminAboutNewChangeRequest(savedRequest);
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

  @GetMapping("/private/profile/link/cancel")
  public String cancelChangeLink(Authentication authentication) {
    final Profile profile = sessionService.getProfile(authentication);
    changeRepository.findByProfileIdAndChangeTypeAndChangeStatus(
        profile.id(), ProfileChangeType.CHANGE_LINK, ProfileChangeStatus.CREATED
      )
      .map(ProfileChangeRequest::cancel)
      .ifPresent(changeRepository::save);

    return "redirect:/private/profile";
  }

  @GetMapping("/private/profile/deactivate/cancel")
  public String cancelDeactivation(Authentication authentication) {
    final Profile profile = sessionService.getProfile(authentication);
    changeRepository.findByProfileIdAndChangeTypeAndChangeStatus(
        profile.id(), ProfileChangeType.PROFILE_DEACTIVATE, ProfileChangeStatus.CREATED
      )
      .map(ProfileChangeRequest::cancel)
      .ifPresent(changeRepository::save);

    return "redirect:/private/profile";
  }

  private void notifyAdminAboutNewChangeRequest(@NonNull ProfileChangeRequest request) {
    final MailTemplate<ProfileChangeRequest> template = templateService.profileChangeRequestCreated();
    final MailSendResult result = mailService.send(template, request);

    checkArgument(result.isOk(), "Failed to send email to admin about new change request");
  }
}
