package dev.abarmin.aml.e2e.page.dashboard;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class HeaderAuthenticated {

  public HeaderAuthenticated() {
    $(byTagAndText("a", "DASHBOARD")).shouldBe(visible);
    $(byTagAndText("a", "PROFILE")).shouldBe(visible);
    $(byTagAndText("a", "LOGOUT")).shouldBe(visible);
  }
}
