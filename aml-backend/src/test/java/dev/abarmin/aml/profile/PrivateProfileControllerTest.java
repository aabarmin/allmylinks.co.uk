package dev.abarmin.aml.profile;

import dev.abarmin.aml.BaseIntegrationTest;
import dev.abarmin.aml.registration.TestCredentials;
import dev.abarmin.aml.registration.TestRegistrationService;
import dev.abarmin.aml.utils.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

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
  }

  @Test
  void changeLink_whenUserRegistered_thenCanRequestLinkChange() throws Exception {
    final TestCredentials user = registrationService.getRegisteredUser();
    mockMvc.perform(post("/private/profile/link")
        .formField("newLink", RandomStringUtils.secure().nextAlphabetic(10))
        .with(user(user.username()).password(user.password()))
        .with(csrf()))
      .andExpect(status().is3xxRedirection());
  }
}
