package dev.abarmin.aml.dashboard.rest;

import dev.abarmin.aml.dashboard.SessionService;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.BlockProps;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.dashboard.rest.converter.BlockPropsConverter;
import dev.abarmin.aml.dashboard.rest.converter.BlockResponseConverter;
import dev.abarmin.aml.dashboard.rest.request.BlockPropsUpdateRequest;
import dev.abarmin.aml.dashboard.rest.response.BlockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/blocks")
public class BlockRestController {

  private final SessionService sessionService;
  private final BlockRepository blockRepository;
  private final BlockResponseConverter blockConverter;
  private final BlockPropsConverter propsConverter;
  private final TransactionTemplate transactionTemplate;

  @GetMapping("/{blockId}")
  public BlockResponse getBlock(@PathVariable long blockId,
                                Authentication authentication) {

    sessionService.getProfile(authentication);
    final Block block = blockRepository.findById(blockId).orElseThrow();
    return blockConverter.convert(block);
  }

  @PutMapping("/{blockId}/props")
  public BlockResponse updateProperties(@PathVariable long blockId,
                                        @RequestBody BlockPropsUpdateRequest request,
                                        Authentication authentication) {

    sessionService.getProfile(authentication);
    final Block block = blockRepository.findById(blockId).orElseThrow();
    final BlockProps props = propsConverter.convert(request.blockProps());
    final Block updatedBlock = block.withProps(props);
    final Block savedBlock = blockRepository.save(updatedBlock);
    return blockConverter.convert(savedBlock);
  }

  @PutMapping("/{blockId}/move-up")
  public BlockResponse moveUp(@PathVariable long blockId,
                              Authentication authentication) {

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

    return blockConverter.convert(currentBlock);
  }

  @PutMapping("/{blockId}/move-down")
  public BlockResponse moveDown(@PathVariable long blockId,
                                Authentication authentication) {

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

    return blockConverter.convert(currentBlock);
  }

  @DeleteMapping("/{blockId}")
  public ResponseEntity<Void> deleteBlock(@PathVariable long blockId,
                                          Authentication authentication) {

    sessionService.getProfile(authentication);
    final Block block = blockRepository.findById(blockId).orElseThrow();
    blockRepository.save(block.withDeleted());
    return ResponseEntity.ok().build();
  }
}
