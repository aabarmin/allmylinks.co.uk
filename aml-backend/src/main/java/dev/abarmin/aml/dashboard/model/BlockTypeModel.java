package dev.abarmin.aml.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlockTypeModel {
  private String name;
  private String icon;
  private String type;
  private String previewComponent;
  private String configComponent;
}
