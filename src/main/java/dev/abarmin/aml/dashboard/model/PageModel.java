package dev.abarmin.aml.dashboard.model;

import java.util.Collection;

public record PageModel(
  long pageId,
  String pageTitle,
  Collection<BlockModel> pageBlocks
) {
}
