package dev.abarmin.aml.profile;

import dev.abarmin.aml.BaseIntegrationTest;
import dev.abarmin.aml.profile.domain.ChangeEmailRequest;
import dev.abarmin.aml.profile.domain.ChangeLinkRequest;
import dev.abarmin.aml.profile.domain.DeactivateProfileRequest;
import dev.abarmin.aml.profile.domain.ProfileChangeRequest;
import dev.abarmin.aml.profile.domain.ProfileChangeStatus;
import dev.abarmin.aml.profile.domain.ProfileChangeType;
import dev.abarmin.aml.registration.TestCredentials;
import dev.abarmin.aml.registration.TestRegistrationService;
import dev.abarmin.aml.registration.domain.Account;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.AccountRepository;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import dev.abarmin.aml.registration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static dev.abarmin.aml.utils.RandomUtils.email;
import static dev.abarmin.aml.utils.RandomUtils.prefixedRandom;
import static org.assertj.core.api.Assertions.assertThat;

class ProfileChangeRequestServiceTest extends BaseIntegrationTest {

  @Autowired
  TestRegistrationService registrationService;

  @Autowired
  ProfileChangeRequestService changeRequestService;

  @Autowired
  ProfileChangeRepository repository;

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  ProfileRepository profileRepository;

  @Autowired
  UserRepository userRepository;

  @Test
  void process_deactivateProfile() {
    final TestCredentials user = registrationService.getRegisteredUser();
    final ProfileChangeRequest savedRequest = repository.save(ProfileChangeRequest.builder()
      .profileId(user.currentProfileId())
      .userId(user.userId())
      .changeType(ProfileChangeType.PROFILE_DEACTIVATE)
      .changePayload(DeactivateProfileRequest.builder()
        .reason("randomReason")
        .build())
      .build());

    final ProfileChangeRequest processedRequest = changeRequestService.process(savedRequest);

    assertThat(processedRequest.getChangeStatus()).isEqualTo(ProfileChangeStatus.PROCESSED);

    final List<Account> accounts = accountRepository.findAllByUserId(user.userId());
    assertThat(accounts).isNotEmpty().allSatisfy(account -> {
      assertThat(account.isActive()).isFalse();
    });
  }

  @Test
  void process_changeLink() {
    final TestCredentials user = registrationService.getRegisteredUser();
    final String newLink = prefixedRandom("new-link");
    final ProfileChangeRequest savedRequest = repository.save(ProfileChangeRequest.builder()
      .profileId(user.currentProfileId())
      .userId(user.userId())
      .changeType(ProfileChangeType.CHANGE_LINK)
      .changePayload(ChangeLinkRequest.builder()
        .newLink(newLink)
        .build())
      .build());

    final ProfileChangeRequest processedRequest = changeRequestService.process(savedRequest);

    assertThat(processedRequest.getChangeStatus()).isEqualTo(ProfileChangeStatus.PROCESSED);

    final Profile profile = profileRepository.findById(user.currentProfileId()).orElseThrow();
    assertThat(profile.link()).isEqualTo(newLink);
  }

  @Test
  void process_changeEmail() {
    final TestCredentials user = registrationService.getRegisteredUser();
    final String newEmail = email();
    final ProfileChangeRequest savedRequest = repository.save(ProfileChangeRequest.builder()
      .profileId(user.currentProfileId())
      .userId(user.userId())
      .changeType(ProfileChangeType.CHANGE_EMAIL)
      .changePayload(ChangeEmailRequest.builder()
        .newEmail(newEmail)
        .build())
      .build());

    final ProfileChangeRequest processedRequest = changeRequestService.process(savedRequest);

    assertThat(processedRequest.getChangeStatus()).isEqualTo(ProfileChangeStatus.PROCESSED);

    final User updatedUser = userRepository.findById(user.userId()).orElseThrow();
    assertThat(updatedUser.email()).isEqualTo(newEmail);
  }
}
