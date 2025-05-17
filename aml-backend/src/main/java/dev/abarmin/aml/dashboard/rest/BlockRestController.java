package dev.abarmin.aml.dashboard.rest;

import dev.abarmin.aml.dashboard.SessionService;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.dashboard.rest.converter.BlockResponseConverter;
import dev.abarmin.aml.dashboard.rest.response.BlockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/blocks")
public class BlockRestController {

  private final SessionService sessionService;
  private final BlockRepository blockRepository;
  private final BlockResponseConverter blockConverter;

  @GetMapping("/{blockId}")
  public BlockResponse getBlock(@PathVariable long blockId,
                                Authentication authentication) {

    sessionService.getProfile(authentication);
    final Block block = blockRepository.findById(blockId).orElseThrow();
    return blockConverter.convert(block);
  }


}
