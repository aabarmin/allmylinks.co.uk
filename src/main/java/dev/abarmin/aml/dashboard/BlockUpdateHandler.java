package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.block.BlockPropsSupport;
import dev.abarmin.aml.dashboard.block.header.HeaderBlockPropsForm;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BlockUpdateHandler {
  private final BlockRepository blockRepository;

  @PostMapping(
    value = "/private/dashboard/{pageId}/blocks/{blockId}",
    params = "type=HEADER_BLOCK")
  public String updateHeaderBlock(@PathVariable("pageId") long pageId,
                                  @PathVariable("blockId") long blockId,
                                  @Valid @ModelAttribute("currentBlock") HeaderBlockPropsForm headerBlock,
                                  BindingResult bindingResult) {

    if (!bindingResult.hasErrors()) {
      updateBlock(blockId, headerBlock);
    }

    return String.format("redirect:/private/dashboard/%s/blocks/%s", pageId, blockId);
  }

  private void updateBlock(long blockId, BlockPropsSupport props) {
    blockRepository.findById(blockId)
      .map(block -> {
        final Block withNewProps = block.withProps(props.toProps());
        return blockRepository.save(withNewProps);
      });
  }
}

