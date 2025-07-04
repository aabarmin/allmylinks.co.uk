package dev.abarmin.aml.e2e.scenario;

import dev.abarmin.aml.e2e.fixture.RegistrationFormFixture;
import dev.abarmin.aml.e2e.page.dashboard.DashboardPage;
import dev.abarmin.aml.e2e.page.login.LoginForm;
import dev.abarmin.aml.e2e.page.login.LoginPage;
import dev.abarmin.aml.e2e.page.registration.RegistrationDonePage;
import dev.abarmin.aml.e2e.page.registration.RegistrationForm;
import dev.abarmin.aml.e2e.page.registration.RegistrationPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static dev.abarmin.aml.e2e.Constants.registrationUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationTest extends BaseE2ETest {

  @Test
  void registration_shouldRegisterAndLogicSuccessfully() {
    final RegistrationPage registrationPage = open(registrationUrl(serverPort), RegistrationPage.class);
    final RegistrationForm registrationForm = RegistrationFormFixture.forSuccessRegistration();

    final RegistrationDonePage registrationDone = registrationPage.register(registrationForm);
    final LoginPage loginPage = registrationDone.toLogin();
    final LoginForm loginForm = registrationForm.toLoginForm();

    final DashboardPage dashboardPage = loginPage.login(loginForm);
    assertThat(dashboardPage).isNotNull();
  }

}
