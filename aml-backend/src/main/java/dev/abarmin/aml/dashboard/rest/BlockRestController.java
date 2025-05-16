package dev.abarmin.aml.dashboard.rest;

import dev.abarmin.aml.dashboard.SessionService;
import dev.abarmin.aml.dashboard.converter.BlockConverter;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.model.BlockModel;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/dashboard/blocks")
public class BlockRestController {

  private final SessionService sessionService;
  private final BlockRepository blockRepository;
  private final BlockConverter blockConverter;

  @GetMapping("/{blockId}")
  public BlockModel getBlock(@PathVariable long blockId,
                             Authentication authentication) {

    sessionService.getProfile(authentication);
    final Block block = blockRepository.findById(blockId).orElseThrow();
    return blockConverter.convert(block);
  }


}
