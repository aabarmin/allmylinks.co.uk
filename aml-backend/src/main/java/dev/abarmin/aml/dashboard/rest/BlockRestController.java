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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/blocks")
public class BlockRestController {

  private final SessionService sessionService;
  private final BlockRepository blockRepository;
  private final BlockResponseConverter blockConverter;
  private final BlockPropsConverter propsConverter;

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
}
