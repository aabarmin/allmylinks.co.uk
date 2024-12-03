package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.block.BlockPropsSupport;
import dev.abarmin.aml.dashboard.block.avatar.AvatarBlockProps;
import dev.abarmin.aml.dashboard.block.avatar.AvatarBlockPropsForm;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class BlockUpdateHandler {
  private static final String UPDATE_BLOCK_ENDPOINT = "/private/dashboard/{pageId}/blocks/{blockId}";

  private final BlockRepository blockRepository;

  @PostMapping(
    value = UPDATE_BLOCK_ENDPOINT,
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

  @PostMapping(
    value = UPDATE_BLOCK_ENDPOINT,
    params = "type=AVATAR_BLOCK"
  )
  public String updateAvatarBlock(@PathVariable("pageId") long pageId,
                                  @PathVariable("blockId") long blockId,
                                  @RequestParam(value = "resetAvatar", required = false, defaultValue = "false") boolean resetAvatar,
                                  @RequestParam(value = "newAvatar", required = false) MultipartFile newAvatar,
                                  @Valid @ModelAttribute("currentBlock") AvatarBlockPropsForm avatarBlock,
                                  BindingResult bindingResult) {

    if (resetAvatar) {
      avatarBlock.getCurrentBlock().getBlockProps().setImageUrl(AvatarBlockProps.DEFAULT_AVATAR);
    } if (newAvatar.isEmpty()) {
      // todo: integrate with the file upload service
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

