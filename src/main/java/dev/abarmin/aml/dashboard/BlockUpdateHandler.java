package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.block.avatar.AvatarBlockProps;
import dev.abarmin.aml.dashboard.block.social.SocialNetworkLink;
import dev.abarmin.aml.dashboard.block.social.SocialNetworksBlockProps;
import dev.abarmin.aml.dashboard.converter.BlockConverter;
import dev.abarmin.aml.dashboard.converter.DashboardModelConverter;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.model.BlockModel;
import dev.abarmin.aml.dashboard.model.DashboardModel;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import dev.abarmin.aml.file.FileSaveRequest;
import dev.abarmin.aml.file.FileSaveResponse;
import dev.abarmin.aml.file.FileService;
import dev.abarmin.aml.image.ImagePresets;
import dev.abarmin.aml.image.ImageService;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(BlockUpdateHandler.UPDATE_BLOCK_ENDPOINT)
public class BlockUpdateHandler {
  public static final String UPDATE_BLOCK_ENDPOINT = "/private/dashboard/{pageId}/blocks/{blockId}";

  private final BlockConverter blockConverter;
  private final BlockRepository blockRepository;
  private final ProfileRepository profileRepository;
  private final PageRepository pageRepository;
  private final DashboardModelConverter dashboardConverter;
  private final FileService fileService;
  private final SessionService sessionService;
  private final ImageService imageService;
  private final ImagePresets imagePresets;
  private final AvatarValidator avatarValidator;

  @PostMapping
  public String updateGenericBlock(Model model,
                                   @Valid @ModelAttribute("currentBlock") BlockModel blockModel,
                                   BindingResult bindingResult) {

    if (!bindingResult.hasErrors()) {
      updateBlock(blockModel);
      rebuildViewModel(model, blockModel);
    }
    return "private/dashboard";
  }

  @PostMapping(params = "type=AVATAR_BLOCK")
  public String updateAvatarBlock(Model model,
                                  @ModelAttribute("currentBlock") BlockModel blockModel,
                                  @RequestParam(value = "resetAvatar", required = false, defaultValue = "false") boolean resetAvatar,
                                  @RequestParam(value = "newAvatar", required = false) MultipartFile newAvatar,
                                  Authentication authentication) throws Exception {

    final AvatarValidator.ValidationResult validationResult = avatarValidator.validate(newAvatar);
    if (!validationResult.isOk()) {
      log.info("Avatar validation failed: {}", validationResult.message());
      model.addAttribute("avatarError", validationResult.message());
      return "private/dashboard";
    }

    final Block block = blockRepository.findById(blockModel.getBlockId()).orElseThrow();
    if (block.props() instanceof AvatarBlockProps avatarProps) {
      if (resetAvatar) {
        avatarProps.setAvatarId(AvatarBlockProps.DEFAULT_AVATAR);
      }
      if (!newAvatar.isEmpty()) {
        final User user = sessionService.getUser(authentication);
        final byte[] processedImage = imageService.process(newAvatar.getInputStream(), imagePresets.avatar());
        final FileSaveRequest request = new FileSaveRequest(user, "avatar.jpg", new ByteArrayInputStream(processedImage));
        final FileSaveResponse savedAvatar = fileService.save(request);
        avatarProps.setAvatarId(savedAvatar.fileId());
      }
      blockRepository.save(block.withProps(avatarProps));
    }

    rebuildViewModel(model, blockModel);
    return "private/dashboard";
  }

  @PostMapping(params = {"type=SOCIAL_NETWORKS_BLOCK", "action"})
  public String updateSocialNetworks(@RequestParam("action") String action,
                                     @ModelAttribute("currentBlock") BlockModel blockModel,
                                     Model model) {


    final SocialNetworksBlockProps networksBlock = (SocialNetworksBlockProps) blockModel.getBlockProps();
    if (isAddAction(action)) {
      networksBlock.getLinks().add(new SocialNetworkLink());
    } else if (isRemoveAction(action)) {
      final int toRemoveIndex = getNetworkToRemove(action);
      networksBlock.getLinks().remove(toRemoveIndex);
    }

    final Block block = blockRepository.findById(blockModel.getBlockId()).orElseThrow();
    final Block updatedBlock = block.withProps(networksBlock);

    model.addAttribute(
      "currentBlock",
      blockConverter.convert(updatedBlock)
    );

    return "private/components-blocks/block-social-networks-props :: content";
  }

  private boolean isAddAction(String action) {
    return StringUtils.equalsIgnoreCase(action, "add-network");
  }

  private boolean isRemoveAction(String action) {
    return StringUtils.startsWithIgnoreCase(action, "remove-network");
  }

  private int getNetworkToRemove(String action) {
    if (!isRemoveAction(action)) {
      throw new IllegalArgumentException("Action is not remove-network");
    }
    return Integer.parseInt(StringUtils.substringAfterLast(action, "__"));
  }

  private void updateBlock(BlockModel blockModel) {
    final Block updated = blockConverter.convert(blockModel);
    blockRepository.save(updated);
  }

  private void rebuildViewModel(Model model, BlockModel blockModel) {
    final Page page = pageRepository.findById(blockModel.getPageId()).orElseThrow();
    final Profile profile = profileRepository.findById(page.profileId()).orElseThrow();

    final DashboardModel dashboardModel = dashboardConverter.convert(profile, page);
    model.addAttribute("model", dashboardModel);
    model.addAttribute("currentBlock", blockModel);
  }
}

