package dev.abarmin.aml.dashboard.converter;

import dev.abarmin.aml.dashboard.domain.Block;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class BlockOrderUtils {
  public static boolean isFirst(Block block, List<Block> blocks) {
    return blocks.indexOf(block) == 0;
  }

  public static boolean isLast(Block block, List<Block> blocks) {
    return blocks.indexOf(block) == blocks.size() - 1;
  }
}
