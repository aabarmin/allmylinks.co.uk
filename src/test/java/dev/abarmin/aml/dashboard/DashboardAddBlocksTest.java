package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.BaseIntegrationTest;
import dev.abarmin.aml.TestPageService;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.registration.TestCredentials;
import dev.abarmin.aml.registration.TestRegistrationService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collection;
import java.util.Comparator;

import static dev.abarmin.aml.dashboard.DashboardController.DASHBOARD_ENDPOINT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class DashboardAddBlocksTest extends BaseIntegrationTest {
  @Autowired
  MockMvc mockMvc;

  @Autowired
  TestRegistrationService registrationService;

  @Autowired
  BlockRepository blockRepository;

  @Autowired
  TestPageService pageService;

  @Test
  void dashboard_whenOpened_thenShowsListOfAvailableBlocks() {
    final TestCredentials user = registrationService.getRegisteredUser();
    final Collection<Block> pageBlocks = pageService.getPageBlocks(user.currentProfileId());

    openDashboard(user, r -> r
      .andExpect(model().attribute("model", allOf(
        hasProperty("availableBlocks", hasSize(BlockType.values().length)),
        hasProperty("currentPage", allOf(
          hasProperty("pageBlocks", hasSize(pageBlocks.size()))
        ))
      )))
    );
  }

  @ParameterizedTest
  @EnumSource(BlockType.class)
  void dashboard_whenBlockAdded_thenItShownOnDashboard(BlockType blockType) {
    final TestCredentials user = registrationService.getRegisteredUser();
    final long pageId = pageService.getHomePageId(user);

    final long blockId = addBlock(user, pageId, blockType);

    final Collection<Block> blocks = pageService.getHomePageBlocks(user);

    assertThat(blocks).anySatisfy(b -> {
      assertThat(b.type()).isEqualTo(blockType);
    });

    openDashboard(user, r -> r
      .andExpect(model().attribute("model", allOf(
        hasProperty("currentPage", allOf(
          hasProperty("pageBlocks", hasItem(allOf(
            hasProperty("blockType", hasProperty("type", equalTo(blockType.name())))
          )))
        ))
      )))
    );

    editBlock(user, blockId, r -> {
      // additional block checks
    });
  }

  @SneakyThrows
  private void openDashboard(TestCredentials user, ConsumerWithException check) {
    final ResultActions actions = mockMvc.perform(get(DASHBOARD_ENDPOINT)
        .with(user(user.username()).password(user.password())))
      .andExpect(status().isOk())
      .andExpect(view().name("private/dashboard"))
      .andExpect(model().attributeExists("model"));

    check.accept(actions);
  }

  @SneakyThrows
  private long addBlock(TestCredentials user, long pageId, BlockType blockType) {
    final String url = DASHBOARD_ENDPOINT + "/" + pageId + "/blocks-add/" + blockType.name();
    mockMvc.perform(get(url)
      .with(user(user.username()).password(user.password())))
      .andExpect(status().is3xxRedirection());

    final Block addedBlock = pageService.getPageBlocks(pageId)
      .stream()
      .filter(b -> b.type() == blockType)
      .max(Comparator.comparing(Block::createdAt))
      .orElseThrow();

    return addedBlock.id();
  }

  @SneakyThrows
  private void editBlock(TestCredentials user, long blockId, ConsumerWithException check) {
    final Block block = blockRepository.findById(blockId).orElseThrow();
    final String url = DASHBOARD_ENDPOINT + "/" + block.pageId() + "/blocks/" + blockId;

    final ResultActions actions = mockMvc.perform(get(url)
        .with(user(user.username()).password(user.password())))
      .andExpect(status().isOk())
      .andExpect(view().name("private/dashboard"))
      .andExpect(model().attributeExists("model"));

    check.accept(actions);
  }

  interface ConsumerWithException {
    void accept(ResultActions r) throws Exception;
  }
}
