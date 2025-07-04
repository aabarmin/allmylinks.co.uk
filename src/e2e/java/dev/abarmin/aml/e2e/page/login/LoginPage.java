package dev.abarmin.aml.e2e.page.login;

import dev.abarmin.aml.e2e.page.dashboard.DashboardPage;
import lombok.NonNull;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

  public LoginPage() {
    $(byTagAndText("h1", "Sign In")).shouldBe(visible);
    $(byTagAndText("label", "Email address")).shouldBe(visible);
    $(byTagAndText("label", "Password")).shouldBe(visible);
  }

  public DashboardPage login(@NonNull LoginForm form) {
    $(byName("email")).setValue(form.getEmail());
    $(byName("password")).setValue(form.getPassword());
    $(byTagAndText("button", "Log in")).click();

    return page(DashboardPage.class);
  }

}
