package dev.abarmin.aml.backoffice;

import dev.abarmin.aml.BaseIntegrationTest;
import dev.abarmin.aml.registration.TestCredentials;
import dev.abarmin.aml.registration.TestRegistrationService;
import dev.abarmin.aml.registration.domain.UserRoles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BackofficeTest extends BaseIntegrationTest {
  @Autowired
  MockMvc mockMvc;

  @Autowired
  TestRegistrationService registrationService;

  @Test
  void backoffice_whenOrdinaryUserTriesToAccess_thenGetsNoAccess() throws Exception {
    final TestCredentials user = registrationService.getRegisteredUser();

    mockMvc.perform(get("/backoffice")
        .with(user(user.username()).password(user.password())))
      .andExpect(status().isForbidden());
  }

  @Test
  void backoffice_whenUserWithBackofficeAccessEnters_thenBackofficeOpens() throws Exception {
    final TestCredentials user = registrationService.getRegisteredUser();
    mockMvc.perform(get("/backoffice")
        .with(user(user.username()).password(user.password()).authorities(UserRoles.BACKOFFICE_AUTHORITY)))
      .andExpect(status().isOk());
  }
}
