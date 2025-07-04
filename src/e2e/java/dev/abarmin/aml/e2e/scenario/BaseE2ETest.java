package dev.abarmin.aml.e2e.scenario;

import com.codeborne.selenide.Configuration;
import dev.abarmin.aml.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.thymeleaf.util.StringUtils;

public abstract class BaseE2ETest extends BaseIntegrationTest {

  @LocalServerPort
  protected int serverPort;

  @BeforeAll
  static void beforeAll() {
    if (StringUtils.equalsIgnoreCase(System.getenv("CI"), "true")) {
      Configuration.headless = true;
    }
  }

}
