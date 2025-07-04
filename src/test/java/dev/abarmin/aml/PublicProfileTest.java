package dev.abarmin.aml;

import dev.abarmin.aml.registration.TestCredentials;
import dev.abarmin.aml.registration.TestRegistrationService;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class PublicProfileTest extends BaseIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  TestRegistrationService registrationService;

  @Autowired
  ProfileRepository profileRepository;

  @Test
  void publicProfile_availableAfterRegistration() throws Exception {
    final TestCredentials registeredUser = registrationService.getRegisteredUser();
    final Profile profile = profileRepository.findById(registeredUser.currentProfileId())
      .orElseThrow(() -> new IllegalStateException("Profile not found"));

    mockMvc.perform(get("/l/" + profile.link()))
      .andExpect(status().is2xxSuccessful())
      .andExpect(view().name("l/l"));
  }

  @Test
  void publicProfile_whenUnknownProfileAccessed_thenShowCorrectErrorPage() throws Exception {
    mockMvc.perform(get("/l/unknown"))
      .andExpect(status().isNotFound())
      .andExpect(view().name("l/error-404"));
  }
}
