package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import dev.abarmin.aml.registration.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.google.common.base.Preconditions.checkArgument;

@Controller
@RequiredArgsConstructor
public class BlockAddHandler {
  private final SessionService sessionService;
  private final PageRepository pageRepository;
  private final BlockFactory blockFactory;
  private final BlockRepository blockRepository;

  @GetMapping("/private/dashboard/{pageId}/blocks-add/{blockType}")
  public String addBlock(@PathVariable("pageId") long pageId,
                         @PathVariable("blockType") String blockType,
                         Authentication authentication) {

    final Profile profile = sessionService.getProfile(authentication);
    final Page page = pageRepository.findByIdAndProfileId(pageId, profile.id())
      .orElseThrow(() -> new IllegalArgumentException("Page not found"));

    final Block newBlock = blockFactory.createBlock(BlockType.valueOf(blockType), page);
    final Block savedBlock = blockRepository.save(newBlock);

    checkArgument(savedBlock != null, "Saved block is null");

    return "redirect:/private/dashboard/" + pageId;
  }
}
