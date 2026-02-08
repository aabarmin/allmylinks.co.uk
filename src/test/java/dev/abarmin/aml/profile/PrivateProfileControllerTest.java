package dev.abarmin.aml.profile;

import dev.abarmin.aml.BaseIntegrationTest;
import dev.abarmin.aml.profile.domain.ProfileChangeRequest;
import dev.abarmin.aml.profile.domain.ProfileChangeStatus;
import dev.abarmin.aml.profile.domain.ProfileChangeType;
import dev.abarmin.aml.registration.TestCredentials;
import dev.abarmin.aml.registration.TestRegistrationService;
import dev.abarmin.aml.utils.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class PrivateProfileControllerTest extends BaseIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  TestRegistrationService registrationService;

  @Autowired
  ProfileChangeRepository repository;

  @Test
  void profile_whenUserRegistered_thenUserCanOpenProfilePage() throws Exception {
    final TestCredentials user = registrationService.getRegisteredUser();
    mockMvc.perform(get("/private/profile")
        .with(user(user.username()).password(user.password()))
        .with(csrf()))
      .andExpect(status().isOk())
      .andExpect(view().name("private/profile"));
  }

  @Test
  void changeEmail_whenUserRegistered_thenCanRequestEmailChange() throws Exception {
    final TestCredentials user = registrationService.getRegisteredUser();
    mockMvc.perform(post("/private/profile/email")
      .formField("newEmail", RandomUtils.email())
      .with(user(user.username()).password(user.password()))
      .with(csrf()))
      .andExpect(status().is3xxRedirection());

    assertHasRequestOfType(user.currentProfileId(), ProfileChangeType.CHANGE_EMAIL);
  }

  @Test
  void changeLink_whenUserRegistered_thenCanRequestLinkChange() throws Exception {
    final TestCredentials user = registrationService.getRegisteredUser();
    mockMvc.perform(post("/private/profile/link")
        .formField("newLink", RandomStringUtils.secure().nextAlphabetic(10))
        .with(user(user.username()).password(user.password()))
        .with(csrf()))
      .andExpect(status().is3xxRedirection());

    assertHasRequestOfType(user.currentProfileId(), ProfileChangeType.CHANGE_LINK);
  }

  @Test
  void deactivateProfile_whenUserRegistered_thenCanDeactivateProfile() throws Exception {
    final TestCredentials user = registrationService.getRegisteredUser();
    mockMvc.perform(post("/private/profile/deactivate")
        .formField("reason", RandomStringUtils.secure().nextAlphabetic(10))
        .with(user(user.username()).password(user.password()))
        .with(csrf()))
      .andExpect(status().is3xxRedirection());

    assertHasRequestOfType(user.currentProfileId(), ProfileChangeType.PROFILE_DEACTIVATE);
  }

  private void assertHasRequestOfType(long profileId, ProfileChangeType type) {
    final List<ProfileChangeRequest> requests = repository.findByProfileIdAndChangeType(profileId, type);
    assertThat(requests).isNotEmpty().allSatisfy(request -> {
      assertThat(request.getChangeStatus()).isEqualTo(ProfileChangeStatus.CREATED);
    });
  }
}
