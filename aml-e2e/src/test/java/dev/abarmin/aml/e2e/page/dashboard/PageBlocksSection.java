package dev.abarmin.aml.e2e.page.dashboard;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class PageBlocksSection {

  public PageBlocksSection() {
    $(byTagAndText("button", "Page blocks"))
      .shouldBe(visible)
      .shouldHave(cssClass("accordion-button"));
  }
}
