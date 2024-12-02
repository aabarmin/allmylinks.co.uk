package dev.abarmin.aml.dashboard.block;

import dev.abarmin.aml.dashboard.domain.BlockProps;

public interface BlockPropsSupport<T extends BlockProps> {
  T toProps();
}
