package dev.abarmin.aml.subscribe;

import dev.abarmin.aml.BaseIntegrationTest;
import dev.abarmin.aml.utils.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SubscriptionControllerTest extends BaseIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void subscribe_fromMainPage() throws Exception {
    mockMvc.perform(post("/subscribe")
        .formField("email", RandomUtils.email())
        .with(csrf()))
      .andExpect(status().isOk());
  }

  @Test
  void subscribe_fromPricingPage() throws Exception {
    mockMvc.perform(post("/subscribe-from-pricing")
        .formField("email", RandomUtils.email())
        .with(csrf()))
      .andExpect(status().isOk());
  }
}
