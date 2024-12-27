package dev.abarmin.aml.dashboard.model;

import dev.abarmin.aml.dashboard.domain.PageProps;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
public class PageModel {
  private long pageId;
  private String pageTitle;
  @Valid
  private PageProps pageProps;

  private Collection<BlockModel> pageBlocks = new ArrayList<>();
}
