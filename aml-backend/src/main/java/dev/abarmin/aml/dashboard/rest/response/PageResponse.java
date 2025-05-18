package dev.abarmin.aml.dashboard.rest.response;

import java.util.Collection;

public record PageResponse(
  long pageId,
  String pageTitle,
  PagePropsResponse pageProps,
  Collection<BlockResponse> pageBlocks
) {
}
