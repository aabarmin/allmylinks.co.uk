package dev.abarmin.aml.dashboard.rest;

import dev.abarmin.aml.dashboard.BlockFactory;
import dev.abarmin.aml.dashboard.SessionService;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import dev.abarmin.aml.dashboard.rest.converter.BlockResponseConverter;
import dev.abarmin.aml.dashboard.rest.converter.PageResponseConverter;
import dev.abarmin.aml.dashboard.rest.response.BlockResponse;
import dev.abarmin.aml.dashboard.rest.response.PageResponse;
import dev.abarmin.aml.registration.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/pages")
public class PageRestController {

  private final SessionService sessionService;
  private final PageRepository pageRepository;
  private final BlockFactory blockFactory;
  private final BlockRepository blockRepository;
  private final BlockResponseConverter blockConverter;
  private final PageResponseConverter pageConverter;

  @PostMapping("/{pageId}/blocks")
  public BlockResponse addBlock(@PathVariable long pageId,
                                @RequestBody BlockAddRequest request,
                                Authentication authentication) {

    final Profile profile = sessionService.getProfile(authentication);
    final Page page = pageRepository.findByIdAndProfileId(pageId, profile.id())
      .orElseThrow(() -> new IllegalArgumentException("Page not found"));

    final Block newBlock = blockFactory.createBlock(request.blockType(), page);
    final Block savedBlock = blockRepository.save(newBlock);

    return blockConverter.convert(savedBlock);
  }

  @GetMapping("/{pageId}")
  public PageResponse getPage(@PathVariable long pageId,
                              Authentication authentication) {

    final Profile profile = sessionService.getProfile(authentication);
    final Page page = pageRepository.findByIdAndProfileId(pageId, profile.id())
      .orElseThrow(() -> new IllegalArgumentException("Page not found"));

    return pageConverter.convert(page);
  }

  public record BlockAddRequest(BlockType blockType) {}

}
