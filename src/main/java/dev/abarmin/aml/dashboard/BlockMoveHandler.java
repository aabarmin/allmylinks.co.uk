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
public class BlockMoveHandler {
  private final BlockRepository blockRepository;
  private final TransactionTemplate transactionTemplate;

  @GetMapping("/private/dashboard/{pageId}/blocks/{blockId}/up")
  public String moveBlockUp(@PathVariable("pageId") long pageId,
                            @PathVariable("blockId") long blockId) {

    final Block currentBlock = blockRepository.findById(blockId).orElseThrow();
    final List<Block> allBlocks = blockRepository.findAllVisible(currentBlock.pageId());

    final int currentIndex = allBlocks.indexOf(currentBlock);
    if (currentIndex == -1) {
      throw new IllegalStateException("Block not found in the list");
    } else if (currentIndex == 0) {
      throw new IllegalStateException("Block is already at the top");
    }

    final Block previousBlock = allBlocks.get(currentIndex - 1);
    transactionTemplate.executeWithoutResult(status -> {
      blockRepository.save(currentBlock.withOrder(currentBlock.order() - 1));
      blockRepository.save(previousBlock.withOrder(previousBlock.order() + 1));
    });

    return String.format("redirect:/private/dashboard/%s/blocks/%s", pageId, blockId);
  }

  @GetMapping("/private/dashboard/{pageId}/blocks/{blockId}/down")
  public String moveBlockDown(@PathVariable("pageId") long pageId,
                            @PathVariable("blockId") long blockId) {

    final Block currentBlock = blockRepository.findById(blockId).orElseThrow();
    final List<Block> allBlocks = blockRepository.findAllVisible(currentBlock.pageId());
    final int currentIndex = allBlocks.indexOf(currentBlock);
    if (currentIndex == -1) {
      throw new IllegalStateException("Block not found in the list");
    } else if (currentIndex == allBlocks.size() - 1) {
      throw new IllegalStateException("Block is already at the bottom");
    }
    final Block nextBlock = allBlocks.get(currentIndex + 1);
    transactionTemplate.executeWithoutResult(status -> {
      blockRepository.save(currentBlock.withOrder(currentBlock.order() + 1));
      blockRepository.save(nextBlock.withOrder(nextBlock.order() - 1));
    });

    return String.format("redirect:/private/dashboard/%s/blocks/%s", pageId, blockId);
  }
}
