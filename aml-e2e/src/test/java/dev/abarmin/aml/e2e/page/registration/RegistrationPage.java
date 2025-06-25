package dev.abarmin.aml.e2e.page.registration;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {

  public RegistrationPage() {
    $(byTagAndText("h1", "Registration")).shouldBe(visible);
    $(byTagAndText("label", "Your name")).shouldBe(visible);
    $(byTagAndText("label", "Email address")).shouldBe(visible);
    $(byTagAndText("label", "Password")).shouldBe(visible);
    $(byTagAndText("label", "Short link")).shouldBe(visible);
    $(byTagAndText("label", "I accept the terms and conditions")).shouldBe(visible);
    $(byTagAndText("button", "Register")).shouldBe(visible);
  }

  public RegistrationDonePage register(RegistrationForm form) {
    $(By.id("name")).setValue(form.getName());
    $(By.id("email")).setValue(form.getEmail());
    $(By.id("password")).setValue(form.getPassword());
    $(By.id("shortLink")).setValue(form.getShortLink());
//    $(By.id("acceptTerms")).click();

    $(byTagAndText("button", "Register")).click();

    return page(RegistrationDonePage.class);
  }

}
