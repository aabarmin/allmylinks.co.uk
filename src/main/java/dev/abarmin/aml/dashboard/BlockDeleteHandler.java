package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlockDeleteHandler {
  private final BlockRepository blockRepository;
  private final TransactionTemplate transactionTemplate;

  @GetMapping("/private/dashboard/{pageId}/blocks/{blockId}/delete")
  public String deleteBlock(@PathVariable("pageId") long pageId,
                            @PathVariable("blockId") long blockId) {

    final Block currentBlock = blockRepository.findById(blockId).orElseThrow();
    transactionTemplate.executeWithoutResult(status -> {
      blockRepository.save(currentBlock.withDeleted().withOrder(-1));
      // reorder remaining blocks
      final List<Block> remainingBlocks = blockRepository.findAllVisible(currentBlock.pageId());
      for (int i = 0; i < remainingBlocks.size(); i++) {
        final Block block = remainingBlocks.get(i);
        blockRepository.save(block.withOrder(i));
      }
    });

    return String.format("redirect:/private/dashboard/%s/blocks/%s", pageId, blockId);
  }
}
