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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    params = {"type=SOCIAL_NETWORKS_BLOCK", "action=add-network"})
  public String addSocialNetwork(@PathVariable("pageId") long pageId,
                                 @PathVariable("blockId") long blockId,
                                 Authentication authentication,
                                 @Valid @ModelAttribute("currentBlock") SocialNetworksBlockPropsForm networksBlock,
                                 Model model) {

    networksBlock.getCurrentBlock().getBlockProps().getLinks().add(new SocialNetworkLink());
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
}
