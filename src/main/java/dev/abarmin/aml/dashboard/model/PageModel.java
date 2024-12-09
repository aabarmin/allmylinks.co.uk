package dev.abarmin.aml.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
public class PageModel {
  private long pageId;
  private String pageTitle;
  private Collection<BlockModel> pageBlocks = new ArrayList<>();
}
