package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.block.social.SocialNetworkLink;
import dev.abarmin.aml.dashboard.block.social.SocialNetworksBlockPropsForm;
import dev.abarmin.aml.dashboard.converter.BlockConverter;
import dev.abarmin.aml.dashboard.converter.DashboardModelConverter;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import dev.abarmin.aml.registration.domain.Profile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static dev.abarmin.aml.dashboard.BlockUpdateHandler.UPDATE_BLOCK_ENDPOINT;

@Controller
@RequiredArgsConstructor
public class SocialNetworksBlockUpdateHandler {
  private final BlockRepository blockRepository;
  private final SessionService sessionService;
  private final PageRepository pageRepository;
  private final DashboardModelConverter dashboardConverter;

  @PostMapping(
    value = UPDATE_BLOCK_ENDPOINT,
    params = {"type=SOCIAL_NETWORKS_BLOCK", "action"})
  public String updateSocialNetworks(@PathVariable("pageId") long pageId,
                                     @PathVariable("blockId") long blockId,
                                     Authentication authentication,
                                     @RequestParam("action") String action,
                                     @Valid @ModelAttribute("currentBlock") SocialNetworksBlockPropsForm networksBlock,
                                     Model model) {


    if (isAddAction(action)) {
      networksBlock.getCurrentBlock().getBlockProps().getLinks().add(new SocialNetworkLink());
    } else if (isRemoveAction(action)) {
      final int toRemoveIndex = getNetworkToRemove(action);
      networksBlock.getCurrentBlock().getBlockProps().getLinks().remove(toRemoveIndex);
    }

    final Block block = blockRepository.findById(blockId).orElseThrow();
    final Block updatedBlock = block.withProps(networksBlock.toProps());

    final Profile profile = sessionService.getProfile(authentication);
    final Page currentPage = pageRepository.findById(pageId).orElseThrow();

    model.addAttribute(
      "model",
      dashboardConverter.convert(profile, currentPage, updatedBlock)
    );

    return "private/components-dashboard/dashboard-block-props :: SOCIAL_NETWORKS_BLOCK";
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
}
