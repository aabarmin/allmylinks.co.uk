package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class BlockUpdateHandler {
  private final BlockRepository blockRepository;

  @PostMapping(
    value = "/private/dashboard/{pageId}/blocks/{blockId}",
    params = "type=HEADER_BLOCK")
  public String updateHeaderBlock(@PathVariable("pageId") long pageId,
                                  @PathVariable("blockId") long blockId,
                                  @Valid @ModelAttribute("currentBlock") HeaderBlockPropsForm form,
                                  BindingResult bindingResult) {

    if (!bindingResult.hasErrors()) {
      blockRepository.findById(blockId)
        .map(block -> {
          final Block withNewProps = block.withProps(form.toProps());
          return blockRepository.save(withNewProps);
        });
    }

    return String.format("redirect:/private/dashboard/%s/blocks/%s", pageId, blockId);
  }
}

