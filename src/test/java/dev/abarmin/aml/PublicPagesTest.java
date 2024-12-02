package dev.abarmin.aml;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PublicPagesTest {
  @Autowired
  MockMvc mockMvc;

  @ParameterizedTest
  @ValueSource(strings = {
    "/",
    "/login",
    "/register",
    "/pricing",
    "/legal/privacy-policy",
    "/legal/terms-of-service",
    "/legal/cookie-policy"
  })
  void publicPagesShouldBeAvailable(String endpoint) throws Exception {
    mockMvc.perform(get(endpoint))
      .andExpect(status().is2xxSuccessful());
  }

  @ParameterizedTest
  @ValueSource(strings = {
    "/subscribe",
    "/subscribe-from-pricing"
  })
  void subscriptionWorksWithoutAuth(String endpoint) throws Exception {
    mockMvc.perform(post(endpoint)
        .formField("email", "test@test.com")
        .with(csrf()))
      .andExpect(status().is2xxSuccessful());
  }
}
