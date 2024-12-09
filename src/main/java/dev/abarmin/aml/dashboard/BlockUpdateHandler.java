package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.block.BlockPropsSupport;
import dev.abarmin.aml.dashboard.block.avatar.AvatarBlockProps;
import dev.abarmin.aml.dashboard.block.header.HeaderBlockPropsForm;
import dev.abarmin.aml.dashboard.block.link.LinkButtonBlockPropsForm;
import dev.abarmin.aml.dashboard.block.social.SocialNetworksBlockPropsForm;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.file.FileSaveRequest;
import dev.abarmin.aml.file.FileSaveResponse;
import dev.abarmin.aml.file.FileService;
import dev.abarmin.aml.image.ImagePresets;
import dev.abarmin.aml.image.ImageService;
import dev.abarmin.aml.registration.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Controller
@RequiredArgsConstructor
public class BlockUpdateHandler {
  public static final String UPDATE_BLOCK_ENDPOINT = "/private/dashboard/{pageId}/blocks/{blockId}";

  private final BlockRepository blockRepository;
  private final FileService fileService;
  private final SessionService sessionService;
  private final ImageService imageService;
  private final ImagePresets imagePresets;

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
                                  Authentication authentication) throws Exception {

    final Block block = blockRepository.findById(blockId).orElseThrow();
    if (block.props() instanceof AvatarBlockProps avatarProps) {
      if (resetAvatar) {
        avatarProps.setFileId(AvatarBlockProps.DEFAULT_AVATAR);
      }
      if (!newAvatar.isEmpty()) {
        final User user = sessionService.getUser(authentication);
        final byte[] processedImage = imageService.process(newAvatar.getInputStream(), imagePresets.avatar());
        final FileSaveRequest request = new FileSaveRequest(user, "avatar.jpg", new ByteArrayInputStream(processedImage));
        final FileSaveResponse savedAvatar = fileService.save(request);
        avatarProps.setFileId(savedAvatar.fileId());
      }
      blockRepository.save(block.withProps(avatarProps));
    }

    return String.format("redirect:/private/dashboard/%s/blocks/%s", pageId, blockId);
  }

  @PostMapping(
    value = UPDATE_BLOCK_ENDPOINT,
    params = "type=BUTTON_BLOCK"
  )
  public String updateButtonBlock(@PathVariable("pageId") long pageId,
                                  @PathVariable("blockId") long blockId,
                                  @Valid @ModelAttribute("currentBlock") LinkButtonBlockPropsForm buttonBlock,
                                  BindingResult bindingResult) {

    if (!bindingResult.hasErrors()) {
      updateBlock(blockId, buttonBlock);
    }

    return String.format("redirect:/private/dashboard/%s/blocks/%s", pageId, blockId);
  }

  @PostMapping(
    value = UPDATE_BLOCK_ENDPOINT,
    params = "type=SOCIAL_NETWORKS_BLOCK"
  )
  public String saveSocialNetworksBlock(@PathVariable("pageId") long pageId,
                                        @PathVariable("blockId") long blockId,
                                        @Valid @ModelAttribute("currentBlock") SocialNetworksBlockPropsForm networks,
                                        BindingResult bindingResult) {

    if (!bindingResult.hasErrors()) {
      updateBlock(blockId, networks);
    }

    return String.format("redirect:/private/dashboard/%s/blocks/%s", pageId, blockId);
  }

  private void updateBlock(long blockId, BlockPropsSupport<?> props) {
    blockRepository.findById(blockId)
      .map(block -> {
        final Block withNewProps = block.withProps(props.toProps());
        return blockRepository.save(withNewProps);
      });
  }
}

