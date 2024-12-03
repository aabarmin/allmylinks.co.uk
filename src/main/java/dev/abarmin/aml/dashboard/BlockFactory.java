package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.block.avatar.AvatarBlockProps;
import dev.abarmin.aml.dashboard.block.header.HeaderBlockProps;
import dev.abarmin.aml.dashboard.block.header.HeaderLevel;
import dev.abarmin.aml.dashboard.block.header.TextAlignment;
import dev.abarmin.aml.dashboard.block.link.LinkButtonBlockProps;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.BlockProps;
import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class BlockFactory {
  private final BlockRepository blockRepository;

  public Block createBlock(BlockType type, Page page) {
    return switch (type) {
      case HEADER_BLOCK -> createHeaderBlock(page);
      case AVATAR_BLOCK -> createAvatarBlock(page);
      case BUTTON_BLOCK -> createLinkButtonBlock(page);
    };
  }

  private Block createLinkButtonBlock(Page page) {
    final LinkButtonBlockProps blockProps = LinkButtonBlockProps.builder()
      .build();

    return createBlock(page, BlockType.BUTTON_BLOCK, blockProps);
  }

  private Block createHeaderBlock(Page page) {
    final HeaderBlockProps blockProps = HeaderBlockProps.builder()
      .level(HeaderLevel.H1)
      .text("Test header")
      .alignment(TextAlignment.CENTER)
      .build();

    return createBlock(page, BlockType.HEADER_BLOCK, blockProps);
  }

  private Block createAvatarBlock(Page page) {
    final AvatarBlockProps blockProps = AvatarBlockProps
      .builder()
      .imageUrl(AvatarBlockProps.DEFAULT_AVATAR)
      .build();

    return createBlock(page, BlockType.AVATAR_BLOCK, blockProps);
  }

  private Block createBlock(Page page, BlockType blockType, BlockProps props) {
    return new Block(
      null,
      page.id(),
      blockType,
      nextOrder(page),
      false,
      props,
      Instant.now(),
      null
    );
  }

  private int nextOrder(Page page) {
    final List<Block> notDeleted = blockRepository.findAllByPageId(page.id())
      .stream()
      .filter(Predicate.not(Block::isDeleted))
      .toList();

    return notDeleted.size() + 1;
  }
}
