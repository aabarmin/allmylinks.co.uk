package dev.abarmin.aml.e2e.page.dashboard;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class AvailableBlocksSection {

  private final SelenideElement container;

  public AvailableBlocksSection() {
    container = $(byId("blocks-list"));

    $(byTagAndText("button", "Blocks"))
      .shouldBe(visible)
      .shouldHave(cssClass("accordion-button"));

    final List<String> availableBlocks = List.of("Avatar", "Link button", "Header", "Social networks");
    for (String blockName : availableBlocks) {
      container.find(byTagAndText("a", blockName))
        .shouldBe(visible);
    }
  }
}
