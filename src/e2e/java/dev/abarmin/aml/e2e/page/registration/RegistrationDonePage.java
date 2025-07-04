package dev.abarmin.aml.e2e.page.registration;

import dev.abarmin.aml.e2e.page.login.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationDonePage {

  public RegistrationDonePage() {
    $(byTagAndText("h1", "Registration completed")).shouldBe(visible);
    $(byTagAndText("button", "Log in"));
  }

  public LoginPage toLogin() {
    $(byTagAndText("a", "Log in")).click();

    return page(LoginPage.class);
  }

}
