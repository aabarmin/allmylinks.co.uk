package dev.abarmin.aml.registration;

import dev.abarmin.aml.BaseIntegrationTest;
import dev.abarmin.aml.registration.repository.UserRepository;
import dev.abarmin.aml.utils.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RegistrationControllerTest extends BaseIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  UserRepository userRepository;

  @Autowired
  TestRegistrationService registrationService;

  @Test
  void registration_registrationFormOpens() throws Exception {
    mockMvc.perform(get("/register"))
      .andExpect(status().is2xxSuccessful());
  }

  @Test
  void registration_whenInvalidValuesProvided_returnsValidationErrors() throws Exception {
    mockMvc.perform(post("/register")
        .formField("name", "")
        .with(csrf())
      )
      .andExpect(status().is2xxSuccessful())
      .andExpect(model().attributeHasErrors("form"));
  }

  @Test
  void registration_whenValidaValuesProvided_shouldCreateUserAccount() throws Exception {
    final String email = RandomUtils.email();
    final String password = RandomStringUtils.secure().nextAlphabetic(5);

    mockMvc.perform(post("/register")
        .formField("name", "Test User")
        .formField("email", email)
        .formField("password", password)
        .formField("link", RandomStringUtils.secure().nextAlphabetic(5).toLowerCase())
        .formField("acceptTerms", "true")
        .with(csrf()))
      .andExpect(status().is2xxSuccessful())
      .andExpect(model().hasNoErrors());

    userRepository.findByEmail(email).orElseThrow();

    mockMvc.perform(formLogin("/login")
        .userParameter("email")
        .user(email)
        .password(password))
      .andExpect(authenticated().withRoles("USER"))
      .andExpect(status().is3xxRedirection());
  }

  @Test
  void registration_whenRegisteredIn_thenCanAccessDashboard() throws Exception {
    final TestCredentials credentials = registrationService.getRegisteredUser();

    mockMvc.perform(get("/private/dashboard")
        .with(user(credentials.username()).password(credentials.password())))
      .andExpect(model().hasNoErrors())
      .andExpect(view().name("private/dashboard"))
      .andExpect(status().is2xxSuccessful());
  }

  @Test
  void registration_whenShortLinkProvided_thenFormHasShortLinkFilledIn() throws Exception {
    mockMvc.perform(get("/register?link=someLink"))
      .andExpect(model().attribute("form", hasProperty("link", equalTo("someLink"))))
      .andExpect(status().is2xxSuccessful())
      .andExpect(view().name("public/registration"));
  }
}
