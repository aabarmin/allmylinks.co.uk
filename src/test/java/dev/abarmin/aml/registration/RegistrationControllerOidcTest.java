package dev.abarmin.aml.registration;

import dev.abarmin.aml.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegistrationControllerOidcTest extends BaseIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void registration_whenNewUserLogsIn_thenOpenPostRegistrationPage() throws Exception {
    mockMvc.perform(get("/register/oidc")
      .with(oidcLogin().authorities(List.of(
        new SimpleGrantedAuthority("OIDC_USER"),
        new SimpleGrantedAuthority("ROLE_USER")
      ))))
      .andExpect(status().is2xxSuccessful());
  }
}
