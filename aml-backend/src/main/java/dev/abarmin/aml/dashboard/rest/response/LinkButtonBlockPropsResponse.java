package dev.abarmin.aml.dashboard.rest.response;

import dev.abarmin.aml.dashboard.block.button.LinkButtonColor;
import dev.abarmin.aml.dashboard.block.button.LinkButtonSize;

public record LinkButtonBlockPropsResponse(
  String text,
  String link,
  LinkButtonSize size,
  LinkButtonColor color
) implements BlockPropsResponse {
}
